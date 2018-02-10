package Example;

import Encrypter.*;

public class Example4 {

    public static void main(String[] args) {
//        String a = "a b c";
//
//        Encrypt e = new Encrypt("temp", false);
//
//        String temp = e.encrypt(a);
//        System.out.println(temp);
//
//        Encrypt d = new Encrypt("temp", false);
//        String res = d.decrypt(temp);
//        System.out.println(res);

        int chunkSize = 4;
        byte[] mainFileContent = " a b c ".getBytes();

        for (int i=0; i<mainFileContent.length; i+=chunkSize) {
            byte[] temp = new byte[(i+chunkSize < mainFileContent.length ? chunkSize : mainFileContent.length - i)];
            System.arraycopy(mainFileContent, i, temp, 0, (i+chunkSize < mainFileContent.length ? chunkSize : mainFileContent.length - i));
            String str = new String(temp);
            System.out.println(":" + str + ":");
        }

        System.out.println(new String(mainFileContent) + "asd");

    }

}
