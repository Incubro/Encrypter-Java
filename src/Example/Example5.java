package Example;

import Server.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Example5 {

    public static void main(String args[]) {

        try {

            Client client = new Client("localhost", 25000);
//            System.out.println(client.sendFile("temp.txt", new String(Files.readAllBytes(Paths.get("temp.txt")))));
            System.out.println(client.receiveFile("cc529c9ceb5290ddc7188fc86144d3b6"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
