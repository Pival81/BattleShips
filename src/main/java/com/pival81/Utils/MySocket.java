package com.pival81.Utils;

import java.io.*;
import java.net.Socket;

public class MySocket extends Thread {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public MySocket(Socket socket) throws IOException {
        this.socket = socket;

        InputStreamReader inStream = new InputStreamReader(socket.getInputStream());
        input = new BufferedReader(inStream);

        OutputStreamWriter outStream = new OutputStreamWriter(socket.getOutputStream());
        output = new PrintWriter(new BufferedWriter(outStream), true);
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String str){
        output.println(str);
    }

    public String read() {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
