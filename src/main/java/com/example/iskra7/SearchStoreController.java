package com.example.iskra7;

import com.example.iskra7.utils.FileUtils;
import hr.java.production.model.Category;
import hr.java.production.model.Discount;
import hr.java.production.model.Item;
import hr.java.production.model.Store;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class SearchStoreController {

    @FXML
    private TextField storeNameTextField;

    @FXML
    private TableView<Store> storeTableView;

    @FXML
    private TableColumn<Store, String> nameTableColumn;


    @FXML
    public void initialize() {
        List<Category> categories=null;
        List<Item> items=null;
        List<Store> stores=null;
        try {
            categories= FileUtils.getCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            items=FileUtils.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            stores=FileUtils.getStores();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<Store> storeObservableList= FXCollections.observableList(stores);
        storeTableView.setItems(storeObservableList);

        nameTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getName());
        });
    }

    @FXML
    protected void onSearchButtonClick() {
        String nameInput=storeNameTextField.getText();

        List<Store> filteredList= null;
        try {
            filteredList=FileUtils.getStores();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!nameInput.isBlank()) {
            filteredList=filteredList.stream()
                    .filter(store -> store.getName().toLowerCase().contains(nameInput.toLowerCase()))
                    .collect(Collectors.toList());
        }

        storeTableView.setItems(FXCollections.observableList(filteredList));
    }
}
