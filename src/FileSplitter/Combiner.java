package FileSplitter;

import Encrypter.Encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Combiner {

    private String indexFileHash;
    private ArrayList<IndexedComponent> indexedComponentList;
    private String path;

    public Combiner(String indexFileHash, String path) {
        this.indexFileHash = indexFileHash;
        indexedComponentList = new ArrayList<>();
        this.path = path;
    }

    public void loadIndex() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(indexFileHash);
        ObjectInputStream ois = new ObjectInputStream(fis);
        indexedComponentList = (ArrayList<IndexedComponent>) ois.readObject();
    }

    public String loadFile() throws IOException {
        String res = "";
        for (IndexedComponent chunk : indexedComponentList) {
            String read = new String (Files.readAllBytes(Paths.get(path + path.charAt(path.length()-1) == "/" ? "" : "/" + chunk.getHashValue())));
            Encrypt e = new Encrypt(read, false);
            res += e.decrypt(chunk.getKey());
        }
        return res;
    }


}
