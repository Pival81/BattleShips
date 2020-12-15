package org.openjfx.Client.Controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.openjfx.Client.App;

import java.io.IOException;

public class MatchController {

    public static boolean[][] grid = new boolean[][]{};

    @FXML public static GridPane mine;
    @FXML public static GridPane opponent;
    public static SimpleBooleanProperty isDisabled = new SimpleBooleanProperty(true);

    @FXML
    public void initialize() throws IOException {
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
