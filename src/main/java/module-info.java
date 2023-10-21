module com.example.donkeykong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.donkeykong to javafx.fxml;
    exports com.example.donkeykong;
}