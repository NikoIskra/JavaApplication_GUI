module com.example.iskra7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;


    opens com.example.iskra7 to javafx.fxml;
    exports com.example.iskra7;
}