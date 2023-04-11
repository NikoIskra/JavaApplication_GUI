package com.example.iskra7;

import com.example.iskra7.utils.FileUtils;
import hr.java.production.model.Category;
import hr.java.production.model.Discount;
import hr.java.production.model.Item;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddNewItemController {

    @FXML
    private TextField itemNameTextField;

    @FXML
    private ChoiceBox<Category> categoryComboBox;

    @FXML
    private TextField itemWidthTextField;

    @FXML
    private TextField itemHeightTextField;

    @FXML
    private TextField itemLengthTextField;

    @FXML
    private TextField itemProductionCostTextField;

    @FXML
    private TextField itemSellingPriceTextField;

    @FXML
    private TextField itemDiscountTextField;

    @FXML
    public void initialize() {
        List<Category> categories=null;
        try {
            categories= FileUtils.getCategories();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        categoryComboBox.setItems(FXCollections.observableList(categories));
    }

    @FXML
    protected void onSaveItemButtonClick() {
        StringBuilder errorMessages=new StringBuilder();
        String itemName=itemNameTextField.getText();
        Optional<Category> categoryChoice=Optional.ofNullable(categoryComboBox.getValue());
        String itemWidth=itemWidthTextField.getText();
        String itemHeight=itemHeightTextField.getText();
        String itemLength=itemLengthTextField.getText();
        String itemCost=itemProductionCostTextField.getText();
        String itemSellingPrice=itemSellingPriceTextField.getText();
        String itemDiscountAmount=itemDiscountTextField.getText();
        BigDecimal itemWidthCheck=BigDecimal.ZERO;
        BigDecimal itemHeightCheck=BigDecimal.ZERO;
        BigDecimal itemLengthCheck=BigDecimal.ZERO;
        BigDecimal itemSellingPriceCheck=BigDecimal.ZERO;
        BigDecimal itemCostCheck=BigDecimal.ZERO;
        BigDecimal itemDsicountCheck=BigDecimal.ZERO;

        if (itemName.isEmpty())
            errorMessages.append("Missing item name\n");
        if (categoryChoice.isEmpty())
            errorMessages.append("Category choice missing\n");
        if (itemWidth.isEmpty())
            errorMessages.append("Missing item width\n");
        else {
            try {
                itemWidthCheck=new BigDecimal(itemWidth);
            }
            catch (NumberFormatException e) {
                errorMessages.append("Item width is not in valid format\n");
            }
        }
        if (itemHeight.isEmpty())
            errorMessages.append("Missing item height\n");
        else {
            try {
                itemHeightCheck=new BigDecimal(itemHeight);
            }
            catch (NumberFormatException e) {
                errorMessages.append("Item height is not in valid format\n");
            }
        }
        if (itemLength.isEmpty())
            errorMessages.append("Missing item length\n");
        else {
            try {
                itemLengthCheck = new BigDecimal(itemLength);
            } catch (NumberFormatException e) {
                errorMessages.append("Item length is not in valid format\n");
            }
        }
        if (itemCost.isEmpty())
            errorMessages.append("Missing item production cost\n");
        else {
            try {
                itemCostCheck = new BigDecimal(itemCost);
            } catch (NumberFormatException e) {
                errorMessages.append("Item cost is not in valid format\n");
            }
        }
        if (itemSellingPrice.isEmpty())
            errorMessages.append("Missing item selling price\n");
        else {
            try {
                itemSellingPriceCheck = new BigDecimal(itemSellingPrice);
            } catch (NumberFormatException e) {
                errorMessages.append("Item selling price is not in valid format\n");
            }
        }
        if (itemDiscountAmount.isEmpty())
            errorMessages.append("Missing item discount amount\n");
        else {
            try {
                itemDsicountCheck = new BigDecimal(itemDiscountAmount);
            } catch (NumberFormatException e) {
                errorMessages.append("Item discount amount is not in valid format\n");
            }
        }

        if (errorMessages.isEmpty()) {
            List<Item> items=null;
            try {
                items=FileUtils.getItems();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Long itemId= (long) (items.size() + 1);
            Item item=new Item(itemName, itemId, new Discount(new BigDecimal(itemDiscountAmount)), categoryChoice.get(), new BigDecimal(itemWidth), new BigDecimal(itemHeight), new BigDecimal(itemLength), new BigDecimal(itemCost), new BigDecimal(itemSellingPrice));
            items.add(item);

            try {
                FileUtils.saveItems(items);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving successful");
            alert.setHeaderText("Item data saved");
            alert.setContentText("Item: " + itemName + " saved");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving unsuccessful");
            alert.setHeaderText("Item data not saved");
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }
    }
}
