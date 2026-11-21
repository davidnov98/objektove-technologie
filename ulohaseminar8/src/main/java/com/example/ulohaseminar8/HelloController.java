package com.example.ulohaseminar8;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class HelloController {
    @FXML private TableView<Invoice> tableInvoice;
    @FXML private TableView<InvoiceItems> tableProduct;
    @FXML private TableColumn<Invoice, Number> idCol;
    @FXML private TableColumn<Invoice, String> dateCol;
    @FXML private TableColumn<Invoice, String> statusCol;
    @FXML private TableColumn<InvoiceItems, String> nameCol;
    @FXML private TableColumn<InvoiceItems, String> categoryCol;
    @FXML private TableColumn<InvoiceItems, Number> priceCol;
    @FXML private TableColumn<InvoiceItems, Number> amountCol;
    @FXML private Label totalLabel;

    private ObservableList<Invoice> invoiceData = FXCollections.observableArrayList();


   private ObservableList<InvoiceItems> invoiceItemsData = FXCollections.observableArrayList();

    private final String URL = "jdbc:mysql://localhost:3306/javafx";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private void getInvoices() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                invoiceData.add(new Invoice(rs.getInt("id"),
                        rs.getString("date"), rs.getString("status")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void getInvoiceItems(int invoiceId) {
        try {
            invoiceItemsData.clear();
            Double totalPrice = 0.0;
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "SELECT invoice_items.id as id, id_invoice, amount, product.name as name, " +
                    "price, category.name as category FROM invoice_items " +
                    "JOIN invoice ON invoice_items.id_invoice = invoice.id " +
                    "JOIN product ON invoice_items.id_product = product.id " +
                    "JOIN category ON product.category_id = category.id " +
                    "WHERE id_invoice = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int amount = rs.getInt("amount");
                Double price = rs.getDouble("price");
                totalPrice += (amount * price);
                InvoiceItems ii = new InvoiceItems(rs.getInt("id"),
                        rs.getInt("id_invoice"),
                amount, rs.getString("name"), price, rs.getString("category"));
                invoiceItemsData.add(ii);
            }
            totalLabel.setText(String.format("%.2f EUR", totalPrice));
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        tableInvoice.setItems(invoiceData);
        idCol.setCellValueFactory(c->c.getValue().idProperty());
        dateCol.setCellValueFactory(c->c.getValue().dateProperty());
        statusCol.setCellValueFactory(c->c.getValue().statusProperty());

        tableProduct.setItems(invoiceItemsData);
        nameCol.setCellValueFactory(c->c.getValue().nameProperty());
        categoryCol.setCellValueFactory(c->c.getValue().categoryProperty());
        priceCol.setCellValueFactory(c->c.getValue().priceProperty());
        amountCol.setCellValueFactory(c->c.getValue().amountProperty());
        getInvoices();
        tableProduct.setOnMouseClicked(e-> {
            if (tableInvoice.getSelectionModel().getSelectedItem() != null) {
                int id = tableInvoice.getSelectionModel().getSelectedItem().idProperty().get();
                getInvoiceItems(id);
            }
        });
    }

}
