package Example;

import FileSplitter.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Example2 {

    public static void main(String args[]) {
        try {

            Splitter s = new Splitter("temp.txt", 2);
            s.SplitFile("tempf/");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
