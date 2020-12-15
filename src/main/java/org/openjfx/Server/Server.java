package org.openjfx.Server;

import org.openjfx.Utils.MySocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6239);
        GameLogic game = new GameLogic();

        while(true){
            Socket _socket = server.accept();
            MySocket socket = new MySocket(_socket);
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
