package org.openjfx.Server;


import static java.lang.System.out;

import org.openjfx.Utils.MySocket;
import org.openjfx.Utils.Utils;

public class GameLogic extends Thread {

    class Player {
        private MySocket socket;
        public boolean[][] grid;
        public int points;
        public Player(MySocket sock){
            socket = sock;
            points = 10;
        }
    }

    Player player1;
    Player player2;
    boolean finished = false;

    public boolean isGameFull(){
        if(player1 != null && player2 != null)
            return true;
        return false;
    }

    public void addPlayer(MySocket socket){
        if(player1 == null){
            player1 = new Player(socket);
        } else if(player2 == null){
            player2 = new Player(socket);
        }
    }

    public void setPlayer1Grid(boolean[][] grid){
        player1.grid = grid;
    }

    public void setPlayer2Grid(boolean[][] grid){
        player2.grid = grid;
    }

    public void run(){
        player1.socket.write("STAGE1");
        player2.socket.write("STAGE1");

        setPlayer1Grid(Utils.fromJson(player1.socket.read()));
        Utils.print2dArray(player1.grid);
        setPlayer2Grid(Utils.fromJson(player2.socket.read()));
        Utils.print2dArray(player2.grid);

        player1.socket.write("STAGE2");
        player2.socket.write("STAGE2");

        while (!finished){
            {
                player1.socket.write("URTURN");
                var split = player1.socket.read().split(":");
                var x = Integer.parseInt(split[0]);
                var y = Integer.parseInt(split[1]);
                Player1Move(x, y);
            }
            {
                player2.socket.write("URTURN");
                var split = player2.socket.read().split(":");
                var x = Integer.parseInt(split[0]);
                var y = Integer.parseInt(split[1]);
                Player2Move(x, y);
            }
        }
        player1.socket.write(player2.points == 0 ? "WON" : "LOST");
        player2.socket.write(player1.points == 0 ? "WON" : "LOST");
    }

    public void Player1Move(int x, int y){
        if (player2.grid[x][y]){
            player1.socket.write("HIT:"+x+":"+y);
            player2.socket.write("HITMINE:"+x+":"+y);
            player2.points--;
        }
        else {
            player1.socket.write("MISS:"+x+":"+y);
        }
        if(player2.points == 0)
            finished = true;
    }

    public void Player2Move(int x, int y){
        if (player1.grid[x][y]){
            player2.socket.write("HIT:"+x+":"+y);
            player1.socket.write("HITMINE:"+x+":"+y);
            player1.points--;
        }
        else {
            player2.socket.write("MISS:"+x+":"+y);
        }
        if(player1.points == 0)
            finished = true;
    }
}