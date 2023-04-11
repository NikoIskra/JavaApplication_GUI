package com.example.iskra7;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PodatciController {

    @FXML
    private List<String> tipList= Arrays.asList(new String[] {"Direktorij", "Datoteka"});

    @FXML
    private ComboBox<String> tipComboBox;

    @FXML
    private TextField rootTextField;

    @FXML
    private TextField nazivTextField;

    @FXML
    private TreeView<String> treeView;


    @FXML
    public void initialize() {
        tipComboBox.setItems(FXCollections.observableList(tipList));
        rootTextField.setText("projekt/podatci");
        TreeItem<String> rootItem=new TreeItem<>(rootTextField.getText());
        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
    }

    @FXML
    protected void rootMake() {
        String rootString=rootTextField.getText();
        File rootFile=new File(String.valueOf(rootString));
        if(rootFile.mkdir()) {
            System.out.println("napravljen");
        }
    }

    @FXML
    protected void onButtonClick() {
        Optional<String> tipChoice=Optional.ofNullable(tipComboBox.getValue());
        String naziv=nazivTextField.getText();
        String rootString=rootTextField.getText();
        StringBuilder errorMessages=new StringBuilder();
        TreeItem<String> rootItem=new TreeItem<>(rootTextField.getText());
        if (naziv.isEmpty())
            errorMessages.append("Nedostaje naziv");
        if (tipChoice.isEmpty())
            errorMessages.append("Nedostaje tip");
        if (errorMessages.isEmpty()) {
            if (tipChoice.get().equals("Direktorij")) {
                File newFile=new File (rootString + "/" + naziv);
                if (newFile.mkdir())
                    System.out.println("Napravljen");
                TreeItem<String> child=new TreeItem<>(naziv);
                rootItem.getChildren().add(child);
                treeView.setRoot(rootItem);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Saving successful");
                alert.setHeaderText("File data saved");
                alert.showAndWait();
            }
            else if (tipChoice.get().equals("Datoteka")) {
                File newFile=new File(rootString + "/" + naziv);
                try {
                    newFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TreeItem<String> child=new TreeItem<>(naziv);
                rootItem.getChildren().add(child);
                treeView.setRoot(rootItem);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Saving successful");
                alert.setHeaderText("File data saved");
                alert.showAndWait();
            }
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving unsuccessful");
            alert.setHeaderText("File not saved");
            alert.setContentText(errorMessages.toString());
            alert.showAndWait();
        }
    }

}
