package Example;

import Encrypter.*;

public class Example4 {

    public static void main(String[] args) {
        String a = "jatin";

        Encrypt e = new Encrypt("temp", false);

        String temp = e.encrypt(a);
        System.out.println(temp);

        Encrypt d = new Encrypt("temp", false);
        String res = d.decrypt(temp);
        System.out.println(res);
    }

}
