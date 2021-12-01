package com.ncaabdashboard;

public class NewsStory {
    private String title;
    private int imageId;
    private String synopsis;
    private String urlLink;

    /**
     * Explicit Value Constructor
     * @param title
     * @param imageId
     * @param synopsis
     * @param url
     */
    NewsStory(String title, int imageId, String synopsis, String url) {
        setTitle(title);
        setImageId(imageId);
        setSynopsis(synopsis);
        setUrlLink(url);
    }

    /***********************************************************************************/
    /** Setter and Getter methods **/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }
    /***********************************************************************************/
}
