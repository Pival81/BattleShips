package org.openjfx;

import org.openjfx.Utils.MySocket;

public class GameLogic {

    class Player {
        private MySocket socket;
        public boolean[][] grid;
        public int points;
        public Player(MySocket sock){
            socket = sock;
            points = 0;
        }
    }

    Player player1;
    Player player2;

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
}