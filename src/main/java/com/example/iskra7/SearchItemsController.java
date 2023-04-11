package com.example.iskra7;

import com.example.iskra7.utils.FileUtils;
import hr.java.production.model.Category;
import hr.java.production.model.Discount;
import hr.java.production.model.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


public class SearchItemsController {
    @FXML
    private static final String CATEGORY_FILE_NAME="dat/categories.txt";
    @FXML
    private static final int CATEGORY_FILE_NUMBER_OF_LINES=3;

    @FXML
    private static final String ITEMS_FILE_NAME="dat/items.txt";
    @FXML
    private static final int ITEM_FILE_NUMBER_OF_LINES=9;

    @FXML
    private TextField itemNameTextField;

    @FXML
    private ComboBox<String> categoryChoiceBox;

    @FXML
    private TableView<Item> itemTableView;

    @FXML
    private TableColumn<Item, String> nameTableColumn;

    @FXML
    private TableColumn<Item, String> categoryTableColumn;

    @FXML
    private TableColumn<Item, String> widthTableColumn;

    @FXML
    private TableColumn<Item, String> heightTableColumn;

    @FXML
    private TableColumn<Item, String> lengthTableColumn;

    @FXML
    private TableColumn<Item, String> productionCostsTableColumn;

    @FXML
    private TableColumn<Item, String> sellingPriceTableColumn;


    @FXML
    public void initialize() {
        List<Category> categories=null;
        try {
            categories= FileUtils.getCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Item> items=null;
        try {
            items=FileUtils.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nameTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getName());
        });

        categoryTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCategory().getName());
        });

        widthTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getWidth().toString());
        });

        heightTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getHeight().toString());
        });

        lengthTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getLength().toString());
        });

        productionCostsTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getProductionCost().toString());
        });

        sellingPriceTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getSellingPrice().toString());
        });

        ObservableList<Item> itemObservableList = FXCollections.observableList(items);

        itemTableView.setItems(itemObservableList);

        ObservableList<String> categoryObservableList=FXCollections.observableList(categories.stream()
                .map(category -> category.getName())
                .collect(Collectors.toList()));

        categoryChoiceBox.setItems(categoryObservableList);
    }

    @FXML
    protected void onSearchButtonClick() {
        String nameInput=itemNameTextField.getText();
        Optional<String> categoryChoice =Optional.ofNullable(categoryChoiceBox.getValue());

        List<Item> filteredList=null;
        try {
            filteredList=FileUtils.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Optional.of(nameInput).isPresent() == true) {
            filteredList=filteredList.stream()
                    .filter(item -> item.getName().toLowerCase().contains(nameInput.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (categoryChoice.isPresent()) {
            filteredList=filteredList.stream()
                    .filter(item -> item.getCategory().getName().toLowerCase().contains(categoryChoice.get().toLowerCase()))
                    .collect(Collectors.toList());
        }

        itemTableView.setItems(FXCollections.observableList(filteredList));
    }


}
