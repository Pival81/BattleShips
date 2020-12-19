package com.pival81;

import com.pival81.Client.App;
import com.pival81.Server.Server;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.concurrent.atomic.AtomicReference;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AtomicReference<Thread> serverThread = new AtomicReference<Thread>(null);
        var btn1 = new Button("Client");
        btn1.setOnAction( e -> {
            stage.close();
            try {
                new App().start(new Stage());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        var btn2 = new Button("Start server");
        btn2.setOnAction( e -> {
            if (serverThread.get() == null){
                serverThread.set(new Thread(() -> Server.main(new String[]{})));
                serverThread.get().start();
                btn1.setDisable(true);
                btn2.setText("Stop server");
            } else {
                serverThread.get().interrupt();
                serverThread.set(null);
                btn2.setText("Start server");
                btn1.setDisable(false);
            }
        });
        btn1.setPrefSize(300, 200);
        btn2.setPrefSize(300, 200);

        var vbox = new VBox();
        vbox.getChildren().addAll(btn1, btn2);
        var scene = new Scene(vbox);
        new JMetro(Style.LIGHT).setScene(scene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();
    }
}
