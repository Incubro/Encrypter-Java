package FileSplitter;

import Encrypter.Encrypt;
import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Combiner {

    private String indexFileHash;
    private String fileName;
    private ArrayList<IndexedComponent> indexedComponentList;
    private String path;

    public Combiner(String indexFileHash, String path) {
        this.indexFileHash = indexFileHash;
        indexedComponentList = new ArrayList<>();
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void loadIndex() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(indexFileHash);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Pair<String, ArrayList<IndexedComponent>> obj = (Pair<String, ArrayList<IndexedComponent>>) ois.readObject();
        indexedComponentList = obj.getValue();
        fileName = obj.getKey();
    }

    public String loadFile() throws IOException {
        String res = "";
        for (IndexedComponent chunk : indexedComponentList) {
            String read = new String (Files.readAllBytes(Paths.get(path + (path.charAt(path.length()-1) == '/' ? "" : "/") + chunk.getHashValue())));
            System.out.println(read);
            Encrypt e = new Encrypt(chunk.getKey(), false);
            res += e.decrypt(read);
        }
        return res;
    }


}
