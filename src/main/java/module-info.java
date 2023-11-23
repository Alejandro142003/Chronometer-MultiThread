module chronometer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens chronometer to javafx.fxml;
    exports chronometer;
}
