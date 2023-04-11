package com.example.iskra7;

import com.example.iskra7.utils.FileUtils;
import hr.java.production.model.Item;
import hr.java.production.model.Store;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AddNewStoreController {
    @FXML
    private TextField storeNameTextField;

    @FXML
    private TextField storeWebAddressTextField;

    @FXML
    private ChoiceBox<Item> storeItemsChoiceBox;

    @FXML
    public void initialize() {
        List<Item> items=null;
        try {
            items= FileUtils.getItems();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        storeItemsChoiceBox.setItems(FXCollections.observableList(items));
    }

    @FXML
    protected void onSaveStoreButtonClick() {
        StringBuilder errorMessages=new StringBuilder();
        String storeName=storeNameTextField.getText();
        String storeWebAddress=storeWebAddressTextField.getText();
        Optional<Item> storeItemChoice=Optional.ofNullable(storeItemsChoiceBox.getValue());

        if (storeName.isEmpty())
            errorMessages.append("Missing store name\n");
        if (storeWebAddress.isEmpty())
            errorMessages.append("Missing store web address\n");
        if (storeItemChoice.isEmpty())
            errorMessages.append("Missing store item\n");

        if (errorMessages.isEmpty()) {
            List<Store> stores=null;
            try {
                stores=FileUtils.getStores();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Set<Item> items=new HashSet<>();
            try {
                items.add(FileUtils.getItems().get((int) (storeItemChoice.get().getId()-1)));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Long id= Long.valueOf(stores.size()+1);
            Store store=new Store(storeName, id, storeWebAddress, items);
            stores.add(store);

            try {
                FileUtils.saveStores(stores);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save successful");
            alert.setHeaderText("Store saved");
            alert.setContentText("Store " + storeName + " Saved");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save unsuccessful");
            alert.setHeaderText("Store not saved");
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }
    }
}
