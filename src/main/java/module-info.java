module com.pival81.Client {
    requires java.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.jfxtras.styles.jmetro;

    opens com.pival81.Client.Controllers to javafx.fxml;
    exports com.pival81.Client;
}