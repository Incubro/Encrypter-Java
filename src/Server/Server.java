package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Server extends Thread {

    private ServerSocket ss;

    public Server(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept();
                saveFile(clientSock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Socket clientSock) throws IOException {
        DataInputStream dis = new DataInputStream(clientSock.getInputStream());
        FileOutputStream fos = new FileOutputStream("testfile.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

        byte[] buffer = new byte[4096];

        try {
            while (true) {



                int filesize = 15123; // Send file size in separate msg
                int read = 0;
                int totalRead = 0;
                int remaining = filesize;
                while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                    totalRead += read;
                    remaining -= read;
                    System.out.println("read " + totalRead + " bytes.");
                    fos.write(buffer, 0, read);
                }


            }
        } catch (Exception e){
            fos.close();
            dis.close();
        }


    }

    public static void main(String[] args) {
        Server fs = new Server(1988);
        fs.start();
    }

}
