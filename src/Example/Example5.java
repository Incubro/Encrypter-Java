package Example;

import Server.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Example5 {

    public static void main(String args[]) {

        try {

            Client client = new Client("localhost", 25000);

            System.out.println("Sending File");
            String hash = client.sendFile("temp.txt", new String(Files.readAllBytes(Paths.get("temp.txt"))));

            System.out.println("Rceiving File");
            System.out.println(client.receiveFile(hash));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
