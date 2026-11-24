package com.example.crud2vydaje;

import com.mysql.cj.jdbc.CallableStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class HelloController {

    boolean checkFields() {
        try {
            if (tfPrice.getText() == null || tfPrice.getText().isEmpty()) {
                return false;
            }
            if (datePick1 == null || datePick1.getValue() == null) {
                return false;
            }
            if (cbShop.getSelectionModel().getSelectedItem() == null ||
            cbCategory.getSelectionModel().getSelectedItem() == null) {
                return false;
            }
           Double d = Double.parseDouble(tfPrice.getText());

            return true;

        }catch (Exception e){
            return false;
        }
    }
    @FXML
    private void btnAddNew() {
        if (!checkFields()) {
            return;
        }
        datePick1.commitValue();
        String sql = "INSERT INTO expense (price, date, shop_id, category_id) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, Double.parseDouble(tfPrice.getText()));
            ps.setDate(2, Date.valueOf(datePick1.getValue()));
            ps.setInt(3, cbShop.getValue().getId());
            ps.setInt(4, cbCategory.getValue().getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        refresh();
        clearPanel();
    }
    @FXML
    private void btnRemove() {
        Expense e = lvItems.getSelectionModel().getSelectedItem();
        if (e == null) {
            return;
        }
        Alert a = new Alert(Alert.AlertType.WARNING, "Naozaj chces vymazat produkt s id: " +
                e.getId() + "?", ButtonType.YES, ButtonType.NO);
        a.setHeaderText("Pozor");
        Optional<ButtonType> result = a.showAndWait();
        if (!result.isPresent() ||  result.get() != ButtonType.YES) {
            return;
        }

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM expense WHERE id = ?");
            ps.setInt(1, e.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            refresh();
        }catch (SQLException se) {
            System.out.println(se.getMessage());
        }

    }
    @FXML
    private void onSearch() {
        refreshExpenseList();
    }


    @FXML
    private void btnSave() {
        if (currentId == null || !checkFields())
            return;
        datePick1.commitValue();
        String sql = "UPDATE expense SET price=?, date=?, shop_id=?, category_id=? " +
                "WHERE id=?";
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, Double.parseDouble(tfPrice.getText()));
            ps.setDate(2, Date.valueOf(datePick1.getValue()));
            ps.setInt(3, cbShop.getValue().getId());
            ps.setInt(4, cbCategory.getValue().getId());
            ps.setInt(5, currentId);
            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        refresh();
    }
    private Integer currentId = null;

    @FXML
    private TextField tfSearch, tfPrice;

    @FXML
    private ComboBox<Shop> cbShop;
    @FXML
    private ComboBox<Category> cbCategory;
    @FXML
    private DatePicker datePick1;

    @FXML
    private TableView<Expense> lvItems;

    private ObservableList<Expense> expenseList =  FXCollections.observableArrayList();
    private ObservableList<Shop> shopList =  FXCollections.observableArrayList();
    private ObservableList<Category> categoryList =  FXCollections.observableArrayList();

    @FXML
    private TableColumn<Expense, Number> tcID;
    @FXML
    private TableColumn<Expense, Number> tcPrice;
    @FXML
    private TableColumn<Expense, LocalDate> tcDate;
    @FXML
    private TableColumn<Expense, Shop> tcShop;
    @FXML
    private TableColumn<Expense, Category> tcCategory;

    private final String URL = "jdbc:mysql://localhost:3306/javafx8a";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    @FXML
    private void initialize() {
        lvItems.setItems(expenseList);
        tcID.setCellValueFactory(c->c.getValue().idProperty());
        tcPrice.setCellValueFactory(c->c.getValue().priceProperty());
        tcDate.setCellValueFactory(c->c.getValue().dateProperty());
        tcShop.setCellValueFactory(c->c.getValue().shopProperty());
        tcCategory.setCellValueFactory(c->c.getValue().categoryProperty());
        clearPanel();
        refresh();
        lvItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                clearPanel();
            }
            else {
                setPanel(newValue);
            }
        });
    }
    private void refresh() {
        refreshExpenseList();
        refreshCategoryList();
        refreshShopList();
        cbShop.getSelectionModel().clearSelection();
        cbCategory.getSelectionModel().clearSelection();
    }

    private void clearPanel() {
        currentId = null;
        tfPrice.clear();
        tfSearch.clear();
        lvItems.getSelectionModel().clearSelection();
        datePick1.setValue(null);
        cbCategory.getSelectionModel().clearSelection();
        cbShop.getSelectionModel().clearSelection();
    }
    private void setPanel(Expense expense) {
        currentId = expense.getId();
        tfPrice.setText(""+expense.getPrice());
        datePick1.setValue(expense.getDate());
        for (Category c : categoryList) {
            if (c.getId() == expense.getCategory().getId()) {
                cbCategory.getSelectionModel().select(c);
                break;
            }
        }
        for (Shop s : shopList) {
            if (s.getId() == expense.getShop().getId()) {
                cbShop.getSelectionModel().select(s);
                break;
            }
        }
        lvItems.getSelectionModel().select(expense);


    }


    private void refreshExpenseList() {
        expenseList.clear();
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "SELECT\n" +
                    "    e.id AS id," +
                    "    e.price AS price," +
                    "    e.date AS date," +
                    "    s.id AS shop_id," +
                    "    s.name AS shop_name," +
                    "    c.id AS category_id," +
                    "    c.name AS category_name " +
                    "FROM expense e " +
                    "JOIN shop s ON s.id = e.shop_id " +
                    "JOIN category c ON c.id = e.category_id " +
                    "WHERE c.name LIKE ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tfSearch.getText() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("category_id"),
                        rs.getString("category_name"));
                Shop shop = new Shop(rs.getInt("shop_id"),
                        rs.getString("shop_name"));
                Expense exp =  new Expense(rs.getInt("id"),
                        rs.getDouble("price"),
                        rs.getDate("date").toLocalDate(),
                         shop, c);
                expenseList.add(exp);
            }
            rs.close();
            ps.close();
            con.close();


        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    private void refreshCategoryList() {
        categoryList.clear();
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "SELECT id, name FROM category";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoryList.add(new Category(rs.getInt("id"),
                        rs.getString("name")));
            }
            rs.close();
            ps.close();
            con.close();
            cbCategory.setItems(categoryList);

        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    private void refreshShopList() {
        shopList.clear();
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "SELECT id, name FROM shop";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                shopList.add(new Shop(rs.getInt("id"),
                            rs.getString("name")));
            }
            rs.close();
            ps.close();
            con.close();
            cbShop.setItems(shopList);

        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

}
