package FileSplitter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

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


    public void SplitFile(String folder) throws IOException {
        byte[] mainFileContent = Files.readAllBytes(path);
        for (int i = 0; i < mainFileContent.length; i += chunkSize) {
            byte[] temp = new byte[(i+chunkSize < mainFileContent.length ? chunkSize : mainFileContent.length - i)];
            System.arraycopy(mainFileContent, i, temp, 0, (i+chunkSize < mainFileContent.length ? chunkSize : mainFileContent.length - i));
            String str = new String(temp);

            Encrypt e = new Encrypt(str, false);
            String key = Integer.toString(str.hashCode());
            String encString = e.encrypt(key);

            String uri = folder + (((!folder.equals("")) && (folder.charAt(folder.length()-1) == '/'))? "" : "/") + encString.hashCode();
            File f = new File(uri);
            f.getParentFile().mkdir();
            f.createNewFile();

            Files.write(Paths.get(uri), encString.getBytes());
            indexedData.add(new IndexedComponent("" + encString.hashCode(), key));
        }

        FileOutputStream fos = new FileOutputStream(Integer.toString(indexedData.hashCode()));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(indexedData);
        oos.close();

    }

}