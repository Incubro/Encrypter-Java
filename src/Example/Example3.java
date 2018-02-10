package Example;

import FileSplitter.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Example3 {

    public static void main(String args[]) {
//        byte[] arr = "jatin katyal".getBytes();
//        int chunk = 5;
//        for (int i=0; i<arr.length; i+=chunk) {
//            byte[] temp = new byte[(i+chunk < arr.length ? chunk : arr.length - i)];
//            java.lang.System.arraycopy(arr,i, temp, 0, (i+chunk < arr.length ? chunk : arr.length - i));
//            System.out.println(new String(temp));
//        }

        try {
            Combiner c = new Combiner("7cf08a6088eed7e4566c6104edc60498", "tempf/");
            c.loadIndex();
            String name = c.getFileName();
            String res = c.loadFile();

            Files.write(Paths.get("retreived/" + name), res.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
