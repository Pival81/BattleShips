module org.openjfx.Client {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens org.openjfx.Client.Controllers to javafx.fxml;
    exports org.openjfx.Client;
}