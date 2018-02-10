package Server;

import Encrypter.Encrypt;
import FileSplitter.Combiner;
import FileSplitter.Splitter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {

    private static boolean verify(String id, String pass) {
        return true;
    }

    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");

            //Server is running always. This is done using this while(true) loop
            while (true) {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //Sending the response back to the client.
                OutputStream os = socket.getOutputStream();
                PrintWriter out = new PrintWriter(os, true);

                String req;
                while ((req = br.readLine()) != null) {
                    System.out.println(req);
                    if (req.equals("S")){
                        String name = br.readLine();

                        Encrypt d = new Encrypt("temp", false);
                        String content = d.decrypt(br.readLine());
                        System.out.println(content);

                        Files.write(Paths.get("temp/" + name), content.getBytes());
                        Splitter s = new Splitter("temp/", name, 12);
                        String hash = s.SplitFile("content/");
                        out.println(hash);
                    } else if (req.equals("R")) {
                        String hash = br.readLine();

                        Combiner combiner = new Combiner(hash, "content/");
                        combiner.loadIndex();
                        String name = combiner.getFileName();
                        String res = combiner.loadFile();
                        out.println(name);
                        Encrypt e = new Encrypt("temp", false);
                        out.println(e.encrypt(res));

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }
}
