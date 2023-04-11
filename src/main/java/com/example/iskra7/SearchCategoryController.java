package com.example.iskra7;

import com.example.iskra7.utils.FileUtils;
import hr.java.production.model.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchCategoryController {
    @FXML
    private static final String CATEGORY_FILE_NAME="dat/categories.txt";
    @FXML
    private static final int CATEGORY_FILE_NUMBER_OF_LINES=3;

    @FXML
    private TextField categoryNameTextField;

    @FXML
    private TableView<Category> categoryTableView;

    @FXML
    private TableColumn<Category, String> nameTableColumn;

    @FXML
    public void initialize() {

        List<Category> categories = new ArrayList<>();
        try {
            categories= FileUtils.getCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<Category> categoryObservableList= FXCollections.observableList(categories);
        categoryTableView.setItems(categoryObservableList);

        nameTableColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getName());
        });
    }

    @FXML
    protected void onSearchButtonClick() {
        String nameInput=categoryNameTextField.getText();

        List<Category> filteredList=null;
        try {
            filteredList=FileUtils.getCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!categoryNameTextField.getText().isBlank()) {
            filteredList=filteredList.stream()
                    .filter(category -> category.getName().toLowerCase().contains(nameInput.toLowerCase()))
                    .collect(Collectors.toList());
        }

        categoryTableView.setItems(FXCollections.observableList(filteredList));
    }
}
