package com.pival81.Client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.pival81.Client.App;

public class MatchButtonController {

    @FXML
    private Button button;
    private int x, y;

    @FXML
    public void initialize(){
        button.setOnAction( e -> {
            App.socket.write(x+":"+y);
            MatchController.isDisabled.set(true);
        });
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }
}
