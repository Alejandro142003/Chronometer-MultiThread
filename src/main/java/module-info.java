module chronometer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens chronometer to javafx.fxml;
    exports chronometer;
    exports chronometer.Controllers;
    opens chronometer.Controllers to javafx.fxml;
}
