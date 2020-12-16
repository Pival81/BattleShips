package com.pival81;

import com.pival81.Server.Server;
import com.pival81.Client.App;

import static java.lang.System.out;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        try{
            if(args[0].equals("--server")
                    || args[0].equals("server")
                    || args[0].startsWith("s")){
                Server.main(new String[]{});
            } else if(args[0].equals("--client")
                    || args[0].equals("client")
                    || args[0].startsWith("c")){
                App.launch(App.class, args);
            }
        } catch (ArrayIndexOutOfBoundsException ex){
            out.println("You need to specify either --server or --client.");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
