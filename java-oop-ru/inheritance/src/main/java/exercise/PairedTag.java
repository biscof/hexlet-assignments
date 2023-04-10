package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    String tagBody;
    List<Tag> childTags;

    public PairedTag(String tagName, Map<String, String> attributes, String tagBody, List<Tag> childTags) {
        super(tagName, attributes);
        this.tagBody = tagBody;
        this.childTags = childTags;
    }

    @Override
    public String toString() {
        String tagStr = super.toString();
        String childTagsStr = childTags.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
        tagStr = tagStr.concat(childTagsStr);
        tagStr = tagStr.concat(this.tagBody);
        tagStr = tagStr.concat(String.format("</%s>", this.tagName));
        return tagStr;
    }
}
// END
