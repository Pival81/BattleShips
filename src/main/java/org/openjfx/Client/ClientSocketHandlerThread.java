package org.openjfx.Client;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.openjfx.Client.Controllers.GridSetupController;
import org.openjfx.Client.Controllers.MatchController;
import org.openjfx.Utils.MySocket;
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
                    App.setRoot("gridsetup");
                    out.println("stage1");
                    break;
                }
                case "STAGE2": {
                    MatchController.grid = GridSetupController.battleshipGrid;
                    App.setRoot("match");
                    out.println("stage2");
                    break;
                }
                case "URTURN": {
                    MatchController.isDisabled.set(false);
                    out.println("urturn");
                    break;
                }
                case "WON": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You won!");
                    alert.showAndWait();
                    MatchController.isDisabled.set(true);
                    break;
                }
                case "LOST": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "You lost!");
                    alert.showAndWait();
                    MatchController.isDisabled.set(true);
                    break;
                }
                default: {
                    if (response.startsWith("HIT:") || response.startsWith("MISS:")) {
                        var split = response.split(":");
                        var x = Integer.parseInt(split[1]);
                        var y = Integer.parseInt(split[2]);
                        var btn = (Button) MatchController.opponent.getChildren().get(x * 10 + y);
                        btn.disableProperty().unbind();
                        btn.setDisable(true);
                        btn.getStyleClass().add(response.startsWith("HIT:") ? "hit" : "miss");
                    }
                    if (response.startsWith("HITMINE:") || response.startsWith("MISSMINE:")) {
                        var split = response.split(":");
                        var x = Integer.parseInt(split[1]);
                        var y = Integer.parseInt(split[2]);
                        var btn = (Button) MatchController.mine.getChildren().get(x * 10 + y);
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