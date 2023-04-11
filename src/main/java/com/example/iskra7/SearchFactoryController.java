package com.example.iskra7;

import com.example.iskra7.utils.FileUtils;
import hr.java.production.enumeration.GradoviEnum;
import hr.java.production.model.*;
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

public class SearchFactoryController {
    private static final String CATEGORY_FILE_NAME="dat/categories.txt";
    private static final int CATEGORY_FILE_NUMBER_OF_LINES=3;

    private static final String ITEMS_FILE_NAME="dat/items.txt";
    private static final int ITEM_FILE_NUMBER_OF_LINES=9;

    private static final String ADDRESS_FILE_NAME="dat/addresses.txt";
    private static final int ADDRESS_FILE_NUMBER_OF_LINES=3;

    private static final String FACTORY_FILE_NAME="dat/factories.txt";
    private static final int FACTORY_NUMBER_OF_LINES=4;

    @FXML
    private TextField factoryNameTextField;

    @FXML
    private TableView<Factory> factoryTableView;

    @FXML
    private TableColumn<Factory, String > nameTableColumn;


    @FXML
    public void initialize() {
        List<Factory> factories=null;
        try {
            factories= FileUtils.getFactories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Factory> factoriesObservableList= FXCollections.observableList(factories);
        factoryTableView.setItems(factoriesObservableList);

        nameTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getName());
        });
    }

    @FXML
    protected void onSearchButtonClick() {
        String factoryName=factoryNameTextField.getText();

        List<Factory> filteredList=null;
        try {
            filteredList=FileUtils.getFactories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!factoryName.isBlank()) {
            filteredList=filteredList.stream()
                    .filter(factory -> factory.getName().toLowerCase().contains(factoryName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        factoryTableView.setItems(FXCollections.observableList(filteredList));
    }
}
