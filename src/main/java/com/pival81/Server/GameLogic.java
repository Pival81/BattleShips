package com.pival81.Server;


import com.pival81.Utils.MySocket;
import com.pival81.Utils.Utils;

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
        //Utils.print2dArray(player1.grid);
        setPlayer2Grid(Utils.fromJson(player2.socket.read()));
        //Utils.print2dArray(player2.grid);

        player1.socket.write("STAGE2");
        player2.socket.write("STAGE2");
        boolean whichPlayer = false;
        while (!finished){
            (whichPlayer ? player1 : player2).socket.write("URTURN");
            var split = (whichPlayer ? player1 : player2).socket.read().split(":");
            var x = Integer.parseInt(split[0]);
            var y = Integer.parseInt(split[1]);
            playerMove((whichPlayer ? player1 : player2), (whichPlayer ? player2 : player1), x, y);
            whichPlayer = !whichPlayer;
        }
        player1.socket.write(player2.points == 0 ? "WON" : "LOST");
        player2.socket.write(player1.points == 0 ? "WON" : "LOST");
    }

    public void playerMove(Player pl1, Player pl2, int x, int y){
        if(pl2.grid[x][y]){
            pl1.socket.write("HIT:"+x+":"+y);
            pl2.socket.write("HITMINE:"+x+":"+y);
            pl2.points--;
        } else {
            pl1.socket.write("MISS:"+x+":"+y);
            pl2.socket.write("MISSMINE:"+x+":"+y);
        }
        if (player1.points == 0 || player2.points == 0){
            finished = true;
        }
    }
}