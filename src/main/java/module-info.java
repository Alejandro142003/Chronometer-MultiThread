module chronometer {
    requires javafx.controls;
    requires javafx.fxml;

    opens chronometer to javafx.fxml;
    exports chronometer;
}
