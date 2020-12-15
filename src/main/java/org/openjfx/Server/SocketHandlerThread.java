package org.openjfx.Server;

import com.google.gson.Gson;
import org.openjfx.GameLogic;
import org.openjfx.Utils.MySocket;

import java.io.IOException;
import java.net.Socket;

public class SocketHandlerThread extends Thread {

    private MySocket socket;
    private GameLogic game;

    public SocketHandlerThread(MySocket socket, GameLogic game) throws IOException {
        this.socket = socket;
        this.game = game;
    }

    public void run(){
        while(true){
            String clientLine = null;
            try {
                clientLine = socket.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(clientLine != null)
                System.out.println(clientLine);
            var split = clientLine.split(":", 2);
            if(split[0].equals("submit")){
                var json = new Gson().fromJson(split[1], new boolean[][]{}.getClass());
            }
        }
    }
}
