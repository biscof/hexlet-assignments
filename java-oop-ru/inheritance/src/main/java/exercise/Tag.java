package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    protected String tagName;
    protected Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        String tagStr = String.format("<%s", this.tagName);
        String attributesStr = attributes.entrySet().stream()
                .map(entry -> String.format(" %s=\"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining());
        tagStr = tagStr.concat(attributesStr);
        tagStr = tagStr.concat(">");
        return tagStr;
    }
}
// END
