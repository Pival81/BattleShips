package org.openjfx.Client.Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ButtonController {

    @FXML Button button;
    private PrimaryController priController;
    private boolean[][] grid;
    private int x, y;
    private SimpleIntegerProperty integerProperty;

    /*@FXML
    public void initialize(){
        button.setOnMouseEntered( e -> {
            var buttons = new Button[]{
                priController.getButton(x, y),
                priController.getButton(x, y+1),
                priController.getButton(x, y-1),
            };
            for(var btn : buttons){
               btn.getStyleClass().add("hovered");
            }
        });
        button.setOnMouseExited( e -> {
            var buttons = new Button[]{
                    priController.getButton(x, y),
                    priController.getButton(x, y+1),
                    priController.getButton(x, y-1),
            };
            for(var btn : buttons){
                btn.getStyleClass().remove("hovered");
            }
        });
    }*/

    public void onClick(){
        if (grid[x][y]) {
            grid[x][y] = !grid[x][y];
            integerProperty.set(integerProperty.getValue()+1);
            button.getStyleClass().remove("selected");
        } else {
            if(integerProperty.getValue()<1)
                return;
            grid[x][y] = !grid[x][y];
            button.getStyleClass().add("selected");
            integerProperty.set(integerProperty.getValue()-1);
        }
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setPriController(PrimaryController controller){
        priController = controller;
        grid = priController.battleshipGrid;
        integerProperty = priController.integerProperty;
    }

}

enum Status {
    Unpressed,
    Miss,
    Hit
}