package com.example.filmy_zanre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class HelloController {
    @FXML private TableView<Movie> tvFilm;
    @FXML private TableView<Genre> tvGenre;
    @FXML private TableColumn<Movie, String> cTitle;
    @FXML private TableColumn<Movie, Number> cLength;
    @FXML private TableColumn<Movie, Number> cYear;
    @FXML private TableColumn<Movie, String> cLink;
    @FXML private TableColumn<Genre, String> cGenre;
    @FXML private Label lCount;

    private ObservableList<Movie> movieData = FXCollections.observableArrayList();
    private ObservableList<Genre> genreData = FXCollections.observableArrayList();

    private final String URL = "jdbc:mysql://localhost:3306/movies";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private void getMovies() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM movie");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                movieData.add(new Movie(rs.getInt("id"), rs.getString("title"),
                        rs.getInt("length"), rs.getInt("year"), rs.getString("link")));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void getGenres(Movie movie) {
        try {
            genreData.clear();
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "SELECT genre.id, genre.name FROM movie_genres " +
                    "JOIN genre ON movie_genres.genre_id = genre.id " +
                    "WHERE movie_genres.movie_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, movie.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                genreData.add(new Genre(rs.getInt("id"), rs.getString("name")));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        lCount.setText("Movie " + movie.getTitle() + " in " + genreData.size() + " genres");
    }
    @FXML
    private void initialize() {
        tvFilm.setItems(movieData);
        cTitle.setCellValueFactory(c -> c.getValue().titleProperty());
        cLength.setCellValueFactory(c -> c.getValue().lengthProperty());
        cYear.setCellValueFactory(c -> c.getValue().yearProperty());
        cLink.setCellValueFactory(c -> c.getValue().linkProperty());

        tvGenre.setItems(genreData);
        cGenre.setCellValueFactory(c -> c.getValue().nameProperty());

        getMovies();


        tvFilm.getSelectionModel().selectedItemProperty().addListener((obs, stara, nova) -> {
            if (nova != null)
                getGenres(nova);
        });
    }
}
