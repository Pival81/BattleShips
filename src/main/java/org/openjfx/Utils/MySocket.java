package org.openjfx.Utils;

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
        PrintWriter output = new PrintWriter(new BufferedWriter(outStream), true);
    }

    public void close() throws IOException {
        socket.close();
    }

    public void write(String str){
        output.println(str);
    }

    public String read() throws IOException {
        return input.readLine();
    }
}
