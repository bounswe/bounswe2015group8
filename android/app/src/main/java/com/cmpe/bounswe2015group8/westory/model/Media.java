package com.cmpe.bounswe2015group8.westory.model;

/**
 * Base object for our media.
 * @author xyllan
 * Date: 23.10.2015
 */
public class Media {
    public static final int IMAGE = 0;
    public static final int AUDIO = 1;
    public static final int VIDEO = 2;
    public static final boolean POST = true;
    public static final boolean HERITAGE = false;
    private long id;
    private long postOrHeritageId;
    private String mediaLink;
    private int mediaType;
    private boolean postOrHeritage;

    public Media() {

    }

    public Media(long postOrHeritageId, String mediaLink, int mediaType, boolean postOrHeritage) {
        this.postOrHeritageId = postOrHeritageId;
        this.mediaLink = mediaLink;
        this.mediaType = mediaType;
        this.postOrHeritage = postOrHeritage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostOrHeritageId() {
        return postOrHeritageId;
    }

    public void setPostOrHeritageId(long postOrHeritageId) {
        this.postOrHeritageId = postOrHeritageId;
    }

    public String getMediaLink() {
        return mediaLink;
    }

    public void setMediaLink(String mediaLink) {
        this.mediaLink = mediaLink;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public boolean getPostOrHeritage() {
        return postOrHeritage;
    }

    public void setPostOrHeritage(boolean postOrHeritage) {
        this.postOrHeritage = postOrHeritage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Media media = (Media) o;

        if (id != media.id) return false;
        if (postOrHeritageId != media.postOrHeritageId) return false;
        if (mediaType != media.mediaType) return false;
        if (postOrHeritage != media.postOrHeritage) return false;
        return !(mediaLink != null ? !mediaLink.equals(media.mediaLink) : media.mediaLink != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (postOrHeritageId ^ (postOrHeritageId >>> 32));
        result = 31 * result + (mediaLink != null ? mediaLink.hashCode() : 0);
        result = 31 * result + mediaType;
        result = 31 * result + (postOrHeritage ? 1 : 0);
        return result;
    }
}