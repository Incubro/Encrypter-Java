package Server;

import Encrypter.Encrypt;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private int port;
    InetAddress address;

    public Client (String host, int port) throws IOException {
        this.port = port;
        address = InetAddress.getByName(host);
    }

    public String sendFile(String name, String content) throws IOException {

        Socket socket = new Socket(address, port);
        //Sending Streams
        OutputStream os = socket.getOutputStream();
        PrintWriter out = new PrintWriter(os, true);

        //Receiving Streams
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        //try login
        out.println("S");
        out.println(name);

        Encrypt e = new Encrypt("temp", false);

        out.println(e.encrypt(content));
        String hash = br.readLine();
        socket.close();
        return hash;
    }

    public String receiveFile(String hash) throws IOException {

        Socket socket = new Socket(address, port);
        //Sending Streams
        OutputStream os = socket.getOutputStream();
        PrintWriter out = new PrintWriter(os, true);

        //Receiving Streams
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        //try login
        out.println("R");
        out.println(hash);
        String name = br.readLine();
        String content = new Encrypt("temp", false).decrypt(br.readLine());

        socket.close();
        return content;
    }
}