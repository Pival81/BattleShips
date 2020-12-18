package com.pival81.Client;

import com.pival81.Client.Controllers.MatchController;
import com.pival81.Utils.MySocket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static MySocket socket;
    public static ClientSocketHandlerThread clientSocketHandler;
    public static MatchController matchController;
    public static Runnable pack;

    @Override
    public void start(Stage stage) throws Exception {
        pack = stage::sizeToScene;
        scene = new Scene(loadFXML("lobby"));
        new JMetro(Style.LIGHT).setScene(scene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> System.exit(0));
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