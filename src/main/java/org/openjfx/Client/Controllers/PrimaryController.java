package org.openjfx.Client.Controllers;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.openjfx.Client.App;
import com.google.gson.Gson;


import java.io.IOException;

public class PrimaryController {

    @FXML GridPane griglia;
    @FXML Button submit;
    @FXML Label label;
    SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(10);
    boolean[][] battleshipGrid = new boolean[10][10];

    @FXML
    public void initialize() throws IOException {
        submit.disableProperty().bind(integerProperty.greaterThan(0));
        label.textProperty().bind(Bindings.concat("Ti sono rimaste ", integerProperty.asString(), " celle."));
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                var fxmlLoader = new FXMLLoader(App.class.getResource("button.fxml"));
                Button btn = fxmlLoader.load();
                var controller = fxmlLoader.<ButtonController>getController();
                controller.setPos(i, j);
                controller.setPriController(this);
                griglia.add(btn, i, j);
            }
        }
    }

    public Button getButton(int x, int y){
        return (Button) griglia.getChildren().get(x*10+y);
    }

    public void submit() {
        var json = new Gson().toJson(battleshipGrid);
        App.socket.write("submit:" + json);
    }
}