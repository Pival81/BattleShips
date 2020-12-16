package com.pival81.Client.Controllers;

import com.pival81.Client.App;
import com.pival81.Client.ClientSocketHandlerThread;
import com.pival81.Utils.MySocket;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.InetAddress;
import java.net.Socket;

public class LobbyController {

    @FXML private TextField address;
    @FXML private Spinner<Integer> port;
    @FXML private Label statusLabel;
    @FXML private FontIcon icon;
    @FXML private ProgressIndicator progress;

    @FXML
    public void initialize(){
        port.setValueFactory(new IntegerSpinnerValueFactory(
                1025, 65535, 6239)
        );
    }

    public void join(){
        icon.setVisible(false);
        progress.setVisible(false);
        try {
            Socket _socket = new Socket(InetAddress.getByName(address.getText()), port.getValue());
            App.socket = new MySocket(_socket);
            App.clientSocketHandler = new ClientSocketHandlerThread(App.socket);
            App.clientSocketHandler.start();
        } catch (Exception ex){
            statusLabel.setVisible(true);
            statusLabel.setText("Error trying to connect to server");
            icon.setVisible(true);
            return;
        }
        statusLabel.setVisible(true);
        statusLabel.setText("Waiting for a game...");
        progress.setVisible(true);
    }
}
