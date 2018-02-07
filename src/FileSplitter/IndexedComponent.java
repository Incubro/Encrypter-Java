package FileSplitter;

public class IndexedComponent {
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
