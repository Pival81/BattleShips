package com.pival81.Client;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import com.pival81.Client.Controllers.GridSetupController;
import com.pival81.Client.Controllers.MatchController;
import com.pival81.Utils.MySocket;
import static java.lang.System.out;


public class ClientSocketHandlerThread extends Thread {

    MySocket socket;

    public ClientSocketHandlerThread(MySocket socket){
        this.socket = socket;
    }

    public void run() {
        while (true) {
            String response;
            response = socket.read();
            switch (response){
                case "STAGE1": {
                    Platform.runLater(() -> {
                        App.setRoot("gridsetup");
                        App.pack.run();
                    });
                    out.println("stage1");
                    break;
                }
                case "STAGE2": {
                    MatchController.grid = GridSetupController.battleshipGrid;
                    Platform.runLater(() -> {
                        App.setRoot("match");
                        App.pack.run();
                    });
                    out.println("stage2");
                    break;
                }
                case "URTURN": {
                    MatchController.isDisabled.set(false);
                    Platform.runLater(() -> {
                        MatchController.myTurn.set(true);
                    });
                    out.println("urturn");
                    break;
                }
                case "WON": {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You won!");
                        alert.showAndWait();
                    });
                    MatchController.isDisabled.set(true);
                    MatchController.gameFinished.set(true);
                    break;
                }
                case "LOST": {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You lost!");
                        alert.showAndWait();
                    });
                    MatchController.isDisabled.set(true);
                    MatchController.gameFinished.set(true);
                    break;
                }
                default: {
                    if (response.startsWith("HIT:") || response.startsWith("MISS:")) {
                        var split = response.split(":");
                        var x = Integer.parseInt(split[1]);
                        var y = Integer.parseInt(split[2]);
                        var btn = (Button) App.matchController.opponent.getChildren().get(x * 10 + y);
                        btn.disableProperty().unbind();
                        btn.setDisable(true);
                        btn.getStyleClass().add(response.startsWith("HIT:") ? "hit" : "miss");
                    }
                    if (response.startsWith("HITMINE:") || response.startsWith("MISSMINE:")) {
                        var split = response.split(":");
                        var x = Integer.parseInt(split[1]);
                        var y = Integer.parseInt(split[2]);
                        var btn = (Button) App.matchController.mine.getChildren().get(x * 10 + y);
                        btn.disableProperty().unbind();
                        btn.setDisable(true);
                        btn.getStyleClass().add(response.startsWith("HITMINE:") ? "hitmine" : "missmine");
                    }
                    break;
                }
            }
        }
    }
}