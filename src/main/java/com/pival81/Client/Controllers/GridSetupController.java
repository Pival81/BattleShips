package com.pival81.Client.Controllers;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.pival81.Client.App;
import com.pival81.Utils.Utils;


import java.io.IOException;

public class GridSetupController {

    @FXML GridPane griglia;
    @FXML Button submit;
    @FXML Label label;
    SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(10);
    public static boolean[][] battleshipGrid = new boolean[10][10];

    @FXML
    public void initialize() throws IOException {
        submit.disableProperty().bind(integerProperty.greaterThan(0));
        label.textProperty().bind(Bindings.concat("Ti sono rimaste ", integerProperty.asString(), " celle."));
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                var fxmlLoader = new FXMLLoader(App.class.getResource("button.fxml"));
                var controller = new ButtonController();
                controller.setPos(i, j);
                controller.setPriController(this);
                fxmlLoader.setController(controller);
                Button btn = fxmlLoader.load();
                griglia.add(btn, i, j);
            }
        }
    }

    public Button getButton(int x, int y){
        return (Button) griglia.getChildren().get(x*10+y);
    }

    public void submit() {
        App.socket.write(Utils.toJson(battleshipGrid));
    }
}