module com.pival81.Client {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.kordamp.ikonli.javafx;

    opens com.pival81.Client.Controllers to javafx.fxml;
    exports com.pival81.Client;
}