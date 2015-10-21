package model;

/**
 * Created by xyllan on 21.10.2015.
 */
public class Tag {
    private long id;
    private String tagText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != tag.id) return false;
        if (tagText != null ? !tagText.equals(tag.tagText) : tag.tagText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tagText != null ? tagText.hashCode() : 0);
        return result;
    }
}
