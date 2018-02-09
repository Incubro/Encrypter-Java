package Example;

import FileSplitter.*;

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
            Combiner c = new Combiner("480bfaa8e06f07acae01045a990c77bd", "tempf/");
            c.loadIndex();
            String res = c.loadFile();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
