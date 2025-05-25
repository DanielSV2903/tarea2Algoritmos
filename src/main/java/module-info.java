module ucr.tarea2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens ucr.tarea2 to javafx.fxml;
    exports ucr.tarea2;
    exports controller;
    opens controller to javafx.fxml;
    opens domain.maps to javafx.fxml;
    exports domain.maps;
    exports domain.hash;
}