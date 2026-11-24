package com.example.crudjava;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.Optional;

public class HelloController {
    @FXML private Label lblStatus;
    @FXML private TableView<Product> tv1;
    @FXML private TableColumn<Product, String> tcName;
    @FXML private TableColumn<Product, Double> tcPrice;
    @FXML private TableColumn<Product, Integer> tcQuantity;
    @FXML private TableColumn<Product, String> tcCategory;
    @FXML private TextField tfName;
    @FXML private TextField tfUnitPrice;
    @FXML private TextField tfSearch;
    @FXML private Spinner<Integer> spinQuantity;
    @FXML private ComboBox<Category> cbCategory;

    private ObservableList<Product> productData = FXCollections.observableArrayList();
    private ObservableList<Category> categoryData = FXCollections.observableArrayList();
    private Integer currentId = null;

    private final String URL = "jdbc:mysql://localhost:3306/javafx";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    @FXML
    private void initialize() {
        tv1.setItems(productData);
        cbCategory.setItems(categoryData);
        spinQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000, 1));
        spinQuantity.setEditable(true);

        tcName.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getName()));
        tcPrice.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getPrice()));
        tcQuantity.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getQuantity()));
        tcCategory.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getCategory().getName()));


        tv1.getSelectionModel().selectedItemProperty().addListener((obs, stary, novy) -> {
            if (novy == null)
                clearSidePanel();
            else
                setSidePanel(novy);
        });

        btnRefresh();
    }

    private void clearSidePanel() {
        currentId = null;
        tfName.clear();
        tfUnitPrice.clear();
        spinQuantity.getValueFactory().setValue(1);
        cbCategory.getSelectionModel().clearSelection();
    }

    private void setSidePanel(Product p) {
        currentId = p.getId();
        tfName.setText(p.getName());
        tfUnitPrice.setText("" + p.getPrice());
        spinQuantity.getValueFactory().setValue(p.getQuantity());
        cbCategory.getSelectionModel().clearSelection();
        for (Category c : categoryData) {
            if (c.getId() == p.getCategory().getId()) {
                cbCategory.getSelectionModel().select(c);
                break;
            }
        }
    }


    private boolean formOk() {
        try {
            if (tfName.getText().trim().isEmpty() || cbCategory.getValue() == null) {
                lblStatus.setText("Vypln nazov a kategoriu");
                return false;
            }
            Double.parseDouble(tfUnitPrice.getText().trim().replace(',', '.'));
            spinQuantity.commitValue();
            return true;
        } catch (NumberFormatException e) {
            lblStatus.setText("Neplatna cena alebo pocet");
            return false;
        }
    }


    private void fillStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, tfName.getText().trim());
        ps.setDouble(2, Double.parseDouble(tfUnitPrice.getText().trim().replace(',', '.')));
        ps.setInt(3, spinQuantity.getValue());
        ps.setInt(4, cbCategory.getValue().getId());
    }

    @FXML
    private void btnAdd() {
        if (!formOk())
            return;
        String sql = "INSERT INTO product (name, price, quantity, category_id, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, NOW(), NOW())";
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            fillStatement(ps);
            ps.executeUpdate();
            ps.close();
            con.close();
            btnRefresh();
            lblStatus.setText("Produkt pridany");
        } catch (SQLException e) {
            lblStatus.setText("Chyba: " + e.getMessage());
        }
    }

    @FXML
    private void btnSave() {
        if (currentId == null) {
            lblStatus.setText("Najprv vyber produkt v tabulke");
            return;
        }
        if (!formOk())
            return;
        String sql = "UPDATE product SET name=?, price=?, quantity=?, category_id=?, updated_at=NOW() WHERE id=?";
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            fillStatement(ps);
            ps.setInt(5, currentId);
            ps.executeUpdate();
            ps.close();
            con.close();
            btnRefresh();
            lblStatus.setText("Produkt ulozeny");
        } catch (SQLException e) {
            lblStatus.setText("Chyba: " + e.getMessage());
        }
    }

    @FXML
    private void btnDelete() {
        Product p = tv1.getSelectionModel().getSelectedItem();
        if (p == null) {
            lblStatus.setText("Najprv vyber produkt v tabulke");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Naozaj chces vymazat produkt " + p.getName() + "?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (!result.isPresent() || result.get() != ButtonType.YES) {
            return;
        }

        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE id=?");
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            ps.close();
            con.close();
            btnRefresh();
            lblStatus.setText("Produkt vymazany");
        } catch (SQLException e) {
            lblStatus.setText("Chyba: " + e.getMessage());
        }
    }

    private void refreshCategory() {
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement("SELECT id, name FROM category");
            ResultSet rs = ps.executeQuery();
            categoryData.clear();
            while (rs.next()) {
                categoryData.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            lblStatus.setText("Chyba: " + e.getMessage());
        }
    }

    private void refreshProducts() {
        String sql = "SELECT p.id, p.name, p.price, p.quantity, " +
                "c.id AS category_id, c.name AS category_name " +
                "FROM product p " +
                "JOIN category c ON p.category_id = c.id " +
                "WHERE p.name LIKE ? " +
                "ORDER BY p.name";
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tfSearch.getText() + "%");
            ResultSet rs = ps.executeQuery();
            productData.clear();
            while (rs.next()) {
                productData.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        new Category(rs.getInt("category_id"), rs.getString("category_name"))));
            }
            rs.close();
            ps.close();
            con.close();
            lblStatus.setText("Pocet produktov: " + productData.size());
        } catch (SQLException e) {
            lblStatus.setText("Chyba: " + e.getMessage());
        }
    }

    @FXML
    private void btnRefresh() {
        tfSearch.clear();
        tv1.getSelectionModel().clearSelection();
        clearSidePanel();
        refreshCategory();
        refreshProducts();
    }

    @FXML
    private void searchChanged() {
        refreshProducts();
    }
}