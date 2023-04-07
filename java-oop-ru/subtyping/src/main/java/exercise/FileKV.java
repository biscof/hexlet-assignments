package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String filePath;

    public FileKV(String filePath, Map<String, String> data) {
        this.filePath = filePath;
        save(data);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> temp = retrieve();
        temp.put(key, value);
        save(temp);
    }

    @Override
    public void unset(String key) {
        Map<String, String> temp = retrieve();
        temp.remove(key);
        save(temp);    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> temp = retrieve();
        return temp.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return retrieve();
    }

    private void save(Map<String, String> data) {
        String content = Utils.serialize(data);
        Utils.writeFile(filePath, content);
    }

    private Map<String, String> retrieve() {
        String json = Utils.readFile(filePath);
        return Utils.unserialize(json);
    }
}
// END
