package org.openjfx.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.openjfx.Utils.MySocket;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static MySocket socket;
    public static ClientSocketHandlerThread clientSocketHandler;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Socket _socket = new Socket(InetAddress.getLocalHost(), 6239);
            socket = new MySocket(_socket);
            clientSocketHandler = new ClientSocketHandlerThread(socket);
            clientSocketHandler.start();
        } catch (ConnectException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server doesn't seem to respond");
            alert.showAndWait();
            return;
        }
        scene = new Scene(loadFXML("lobby"));
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            try {
                stop();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        stage.show();
    }

    static void setRoot(String fxml) {
        try {
            var buh = loadFXML(fxml);
            scene.setRoot(buh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}