package com.pival81.Client.Controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.pival81.Client.App;

import java.io.IOException;

public class MatchController {

    public static boolean[][] grid = new boolean[][]{};

    @FXML public GridPane mine;
    @FXML public GridPane opponent;
    @FXML public Label turn;
    public static SimpleBooleanProperty isDisabled = new SimpleBooleanProperty(true);
    public static SimpleBooleanProperty myTurn = new SimpleBooleanProperty(false);
    public static SimpleBooleanProperty gameFinished = new SimpleBooleanProperty(false);

    @FXML
    public void initialize() throws IOException {
        App.matchController = this;
        turn.textProperty().bind(Bindings.createStringBinding(() -> {
            return myTurn.getValue() ? "Your turn" : "Opponent's turn";
        }, myTurn));
        turn.visibleProperty().bind(gameFinished.not());
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                {
                    var fxmlLoader = new FXMLLoader(App.class.getResource("button.fxml"));
                    var controller1 = new MatchButtonController();
                    controller1.setPos(i, j);
                    fxmlLoader.setController(controller1);
                    Button btn = fxmlLoader.load();
                    btn.disableProperty().bind(isDisabled);
                    opponent.getChildren();
                    opponent.add(btn, i, j);
                }
                {
                    var fxmlLoader = new FXMLLoader(App.class.getResource("button.fxml"));
                    Button btn = fxmlLoader.load();
                    if(grid[i][j])
                        btn.getStyleClass().add("selected");
                    mine.add(btn, i, j);
                }
            }
        }
    }
}
