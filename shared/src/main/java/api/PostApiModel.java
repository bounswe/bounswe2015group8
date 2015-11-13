package api;

/**
 * Created by Goktug on 13.11.2015.
 */
public class PostApiModel {
    private long heritageId;
    private int type;
    private String title;
    private String content;

    public PostApiModel(long heritageId, int type, String title, String content) {
        this.heritageId = heritageId;
        this.type = type;
        this.title = title;
        this.content = content;
    }

    public long getHeritageId() {
        return heritageId;
    }

    public void setHeritageId(long heritageId) {
        this.heritageId = heritageId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
