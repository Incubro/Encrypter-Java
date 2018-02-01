package Example;

import Encrypter.Encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Example1 {

    String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    void writeFile(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes());
    }

    public static void main(String args[]) {

        Encrypt encrypt = new Encrypt("something", false);
        String val = encrypt.encrypt("Jatin");
        System.out.println(val);

        Encrypt decrypt = new Encrypt("something", false);
        String val2 = decrypt.decrypt(val);
        System.out.println(val2);
    }

}
