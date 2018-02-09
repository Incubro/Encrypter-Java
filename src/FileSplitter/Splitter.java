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

public class Splitter {

    private Path path;
    private ArrayList<IndexedComponent> indexedData;
    private int chunkSize;

    public Splitter(String path, int chunkSize) {
        this.path = Paths.get(path);
        this.chunkSize = chunkSize;
        indexedData = new ArrayList<>();
    }
    public Splitter(String path) {
        this(path, 1024);
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

    public void SplitFile(String folder) throws IOException {
        byte[] mainFileContent = Files.readAllBytes(path);
        for (int i = 0; i < mainFileContent.length; i += chunkSize) {
            byte[] temp = new byte[(i+chunkSize < mainFileContent.length ? chunkSize : mainFileContent.length - i)];
            System.arraycopy(mainFileContent, i, temp, 0, (i+chunkSize < mainFileContent.length ? chunkSize : mainFileContent.length - i));
            String str = new String(temp);
            System.out.println(str);

            String key = getHash(str);
            Encrypt e = new Encrypt(key, false);
            String encString = e.encrypt(str);

            System.out.println(encString);

            //just a test
            Encrypt d = new Encrypt(key, false);
            System.out.println(d.decrypt(encString));

            String uri = folder + (((!folder.equals("")) && (folder.charAt(folder.length()-1) == '/'))? "" : "/") + getHash(encString);
            File f = new File(uri);
            f.getParentFile().mkdir();
            f.createNewFile();

            Files.write(Paths.get(uri), encString.getBytes());
            indexedData.add(new IndexedComponent("" + getHash(encString), key));
        }

        System.out.println(getHash(indexedData.toString()));
        FileOutputStream fos = new FileOutputStream(getHash(indexedData.toString()));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(indexedData);
        oos.close();

    }

}