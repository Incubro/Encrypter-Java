package Example;

import Encrypter.Encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Example1 {

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    static void writeFile(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes());
    }

    public static void main(String args[]) {

        String filePath = "/Users/jatinkatyal/IdeaProjects/CloudEncrypter/temp.txt";
        String dFilePath = "/Users/jatinkatyal/IdeaProjects/CloudEncrypter/temp.txt";
        String eFilePath = "/Users/jatinkatyal/IdeaProjects/CloudEncrypter/encrypted.txt";

        try {
            Encrypt encrypt = new Encrypt("something", false);
            String val = encrypt.encrypt(new String(java.util.Arrays.toString(readFile(filePath, Charset.forName("UTF-8")).getBytes()).getBytes(), "UTF-8"));
            writeFile(eFilePath, val);

            Encrypt decrypt = new Encrypt("something", false);
            String read = readFile(eFilePath, Charset.forName("UTF-8"));
            String val2 = decrypt.decrypt(read);
            writeFile(dFilePath, val2);
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }

}
