package com.pival81.Client.Controllers;

import com.pival81.Client.App;
import com.pival81.Client.ClientSocketHandlerThread;
import com.pival81.Utils.MySocket;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import jfxtras.styles.jmetro.MDL2IconFont;

import java.net.InetAddress;
import java.net.Socket;

public class LobbyController {

    @FXML private TextField address;
    @FXML private Spinner<Integer> port;
    @FXML private Label statusLabel;
    @FXML private ProgressIndicator progress;
    @FXML private HBox buh;
    private MDL2IconFont mdlicon;

    @FXML
    public void initialize(){
        port.setValueFactory(new IntegerSpinnerValueFactory(
                1025, 65535, 6239)
        );
        mdlicon = new MDL2IconFont("\uE783");
        mdlicon.setSize(32);
        mdlicon.setVisible(false);
        buh.getChildren().add(buh.getChildren().size()-1, mdlicon);
    }

    public void join(){
        mdlicon.setVisible(false);
        progress.setVisible(false);
        try {
            Socket _socket = new Socket(InetAddress.getByName(address.getText()), port.getValue());
            App.socket = new MySocket(_socket);
            App.clientSocketHandler = new ClientSocketHandlerThread(App.socket);
            App.clientSocketHandler.start();
        } catch (Exception ex){
            statusLabel.setVisible(true);
            statusLabel.setText("Error trying to connect to server");
            mdlicon.setVisible(true);
            return;
        }
        statusLabel.setVisible(true);
        statusLabel.setText("Waiting for a game...");
        progress.setVisible(true);
    }
}
