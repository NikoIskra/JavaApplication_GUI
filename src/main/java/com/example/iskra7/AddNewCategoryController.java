package com.example.iskra7;

import com.example.iskra7.utils.FileUtils;
import hr.java.production.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class AddNewCategoryController {
    @FXML
    private TextField categoryNameTextField;

    @FXML
    private TextField categoryDescriptionTextField;

    @FXML
    public void initialize() {}

    @FXML
    protected void onSaveCategoryButtonClick() {
        StringBuilder errorMessages=new StringBuilder();
        String name=categoryNameTextField.getText();
        String description=categoryDescriptionTextField.getText();

        if (name.isEmpty())
            errorMessages.append("Missing category name\n");
        if (description.isEmpty())
            errorMessages.append("Missing category description\n");

        if (errorMessages.isEmpty()) {
            List<Category> categories=null;
            try {
                categories= FileUtils.getCategories();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Long id= Long.valueOf(categories.size()+1);
            Category category=new Category(name, id, description);
            categories.add(category);

            try {
                FileUtils.saveCategories(categories);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving successful");
            alert.setHeaderText("Category saved");
            alert.setContentText("Category " + name + " successfully saved");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving unsuccessful");
            alert.setHeaderText("Category data not saved");
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }
    }
}
