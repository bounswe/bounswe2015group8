package model;

/**
 * Created by xyllan on 23.10.2015.
 */
public class TagHeritage {
    private long tagId;
    private long heritageId;
    private Tag tag;
    private Heritage heritage;

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public long getHeritageId() {
        return heritageId;
    }

    public void setHeritageId(long heritageId) {
        this.heritageId = heritageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagHeritage that = (TagHeritage) o;

        if (tagId != that.tagId) return false;
        if (heritageId != that.heritageId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (int) (heritageId ^ (heritageId >>> 32));
        return result;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Heritage getHeritage() {
        return heritage;
    }

    public void setHeritage(Heritage heritage) {
        this.heritage = heritage;
    }
}
