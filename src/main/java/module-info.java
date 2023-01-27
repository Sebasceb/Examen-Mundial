module com.mycompany.examenmundial {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.examenmundial to javafx.fxml;
    exports com.mycompany.examenmundial;
}
