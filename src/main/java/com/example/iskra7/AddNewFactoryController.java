package com.example.iskra7;

import com.example.iskra7.utils.FileUtils;
import hr.java.production.enumeration.GradoviEnum;
import hr.java.production.model.Address;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.*;

public class AddNewFactoryController {

    @FXML
    private TextField factoryNameTextField;

    @FXML
    private TextField factoryStreetTextField;

    @FXML
    private TextField factoryHouseNumberTextField;

    @FXML
    private TextField factoryCityTextField;

    @FXML
    private ChoiceBox<Item> factoryItemsChoiceBox;

    @FXML
    public void initialize() {
        List<Item> items=null;
        try {
            items= FileUtils.getItems();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        factoryItemsChoiceBox.setItems(FXCollections.observableList(items));
    }

    @FXML
    protected void onSaveFactoryButtonClick() {
        StringBuilder errorMessages=new StringBuilder();
        String factoryName=factoryNameTextField.getText();
        String factoryStreet=factoryStreetTextField.getText();
        String factoryHouseNumber=factoryHouseNumberTextField.getText();
        String factoryCity=factoryCityTextField.getText();
        Optional<Item> factoryItemChoice=Optional.ofNullable(factoryItemsChoiceBox.getValue());

        if (factoryName.isEmpty())
            errorMessages.append("Factory name missing\n");
        if (factoryStreet.isEmpty())
            errorMessages.append("Factory street missing\n");
        if (factoryHouseNumber.isEmpty())
            errorMessages.append("Factory house number missing\n");
        if (!
                Arrays.stream(GradoviEnum.values())
                        .anyMatch(city -> city.getName().equalsIgnoreCase(factoryCity))
        )
            errorMessages.append("Factory city missing\n");

        if (errorMessages.isEmpty()) {
            List<Factory> factories=null;
            try {
                factories=FileUtils.getFactories();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<Item> items=new HashSet<>();
            try {
                items.add(FileUtils.getItems().get((int) (factoryItemChoice.get().getId()-1)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            GradoviEnum grad=GradoviEnum.valueOf(factoryCity.toUpperCase());
            Address address=new Address.Builder(factoryStreet)
                    .atHouseNumber(factoryHouseNumber)
                    .inCity(grad)
                    .build();
            Long id= Long.valueOf(factories.size()+1);
            Factory factory=new Factory(factoryName, id, address, items);
            factories.add(factory);

            try {
                FileUtils.saveFactories(factories);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save successful");
            alert.setHeaderText("Factory saved");
            alert.setContentText("Factory " + factoryName + " saved");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save unsuccessful");
            alert.setHeaderText("Factory not saved");
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }
    }
}
