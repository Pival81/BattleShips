package com.pival81.Server;

import com.pival81.Utils.MySocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(6239);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        GameLogic game = new GameLogic();

        while(true){
            Socket _socket = null;
            MySocket socket = null;
            try {
                _socket = server.accept();
                socket = new MySocket(_socket);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            if(game.isGameFull()){
                socket.write("gamefull");
                socket.close();
                return;
            }
            game.addPlayer(socket);
            if (game.isGameFull())
                game.start();
        }
    }
}
