module com.example.donkeykong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.adampach.donkeykong to javafx.fxml;
    exports com.adampach.donkeykong;
}