package api;

/**
 * Created by Goktug on 13.11.2015.
 */
public class PostApiModel {
    private long heritageId;
    private long ownerId;
    private int type;
    private String title;
    private String content;
    private String place;

    public PostApiModel() {
    }

    public PostApiModel(long heritageId, long ownerId ,int type, String title, String content) {
        this.heritageId = heritageId;
        this.ownerId = ownerId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.place = "";
    }

    public PostApiModel(long heritageId, long ownerId ,int type, String title, String content, String place) {
        this.heritageId = heritageId;
        this.ownerId = ownerId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.place = place;
    }

    public long getHeritageId() {
        return heritageId;
    }

    public void setHeritageId(long heritageId) {
        this.heritageId = heritageId;
    }

    public long getOwnerId() { return ownerId; }

    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
