package FileSplitter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Splitter {

    private class IndexedComponent {
        private String HashValue;
        private String Key;

        public IndexedComponent(){}
        public IndexedComponent(String HashValue, String Key) {
            this.HashValue = HashValue;
            this.Key = Key;
        }

        // setters
        public void setHashValue(String hashValue) {
            HashValue = hashValue;
        }

        public void setKey(String key) {
            Key = key;
        }

        // getter
        public String getHashValue() {
            return HashValue;
        }

        public String getKey() {
            return Key;
        }
    }

    private Path path;
    private ArrayList<IndexedComponent> indexedData;
    private int chunkSize;

    public Splitter(String path, int chunkSize) {
        this.path = Paths.get(path);
        this.chunkSize = chunkSize;
    }
    public Splitter(String path) {
        this(path, 1024);
    }


    public void SplitFile(String folder) throws IOException {
        byte[] mainFileContent = Files.readAllBytes(path);

    }

}