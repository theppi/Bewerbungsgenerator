package de.theppi.generator.data;

public class HtmlMetaInfo {
    private String title;

    public String getTitle() {
        return title != null ? title : "title";
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
