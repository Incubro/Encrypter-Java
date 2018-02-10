package Example;

import FileSplitter.*;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.security.MessageDigest;

public class Example2 {

    public static void main(String args[]) {
        try {


//            String s="is a test";
//           MessageDigest m=MessageDigest.getInstance("MD5");
//           m.update(s.getBytes(),0,s.length());
//           System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));

//            MessageDigest md = MessageDigest.getInstance("MD5");
//            String digest = new String (md.digest("jatin".getBytes("UTF-8")));
//            System.out.println(digest);
            Splitter s = new Splitter("", "temp.txt", 12);
            s.SplitFile("tempf/");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
