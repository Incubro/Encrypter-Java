package FileSplitter;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import java.security.MessageDigest;

import Encrypter.Encrypt;

import Server.Client;
import javafx.util.Pair;

public class Splitter {

    private Path path;
    private ArrayList<IndexedComponent> indexedData;
    private int chunkSize;
    private String fileName;

    private String host = "localhost";
    private int port = 25000;
    private String id = "admin";
    private String pass = "admin";

    private Client client;

    public Splitter(String path, String fileName, int chunkSize) throws IOException {
        this.path = Paths.get(path + fileName);
        this.fileName = fileName;
        this.chunkSize = chunkSize;
        indexedData = new ArrayList<>();
        client = new Client(host, port);
    }
    public Splitter(String path, String fileName) throws IOException {
        this(path, fileName, 1024);
    }

    String getHash(String toDigest) {
        try {
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.update(toDigest.getBytes(),0,toDigest.length());
            return new BigInteger(1,m.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String SplitFile(String folder) throws IOException {
        byte[] mainFileContent = Files.readAllBytes(path);
        for (int i = 0; i < mainFileContent.length; i += chunkSize) {
            byte[] temp = new byte[(i+chunkSize < mainFileContent.length ? chunkSize : mainFileContent.length - i)];
            System.arraycopy(mainFileContent, i, temp, 0, (i+chunkSize < mainFileContent.length ? chunkSize : mainFileContent.length - i));
            String str = new String(temp);

            String key = getHash(str);
            Encrypt e = new Encrypt(key, false);
            String encString = e.encrypt(str);

            String uri = folder + (((!folder.equals("")) && (folder.charAt(folder.length()-1) == '/'))? "" : "/") + getHash(encString);
            File f = new File(uri);
            f.getParentFile().mkdir();
            f.createNewFile();

//            client.sendFile(id, pass, getHash(encString), encString);

            Files.write(Paths.get(uri), encString.getBytes());
            indexedData.add(new IndexedComponent("" + getHash(encString), key));
        }

        System.out.println(getHash(indexedData.toString()));
        FileOutputStream fos = new FileOutputStream(getHash(indexedData.toString()));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Pair<String, ArrayList<IndexedComponent>> obj = new Pair<>(fileName, indexedData);
        oos.writeObject(obj);
        oos.close();
        return getHash(indexedData.toString());
    }

}