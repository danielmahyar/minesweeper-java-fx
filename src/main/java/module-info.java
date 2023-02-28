module com.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.minesweeper to javafx.fxml;
    exports com.minesweeper;
    exports com.minesweeper.utils;
    exports com.minesweeper.models;
}