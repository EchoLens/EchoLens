package models;
import lombok.Data;

@Data
public class ContentListResponse {
    private java.util.List<Content> contents;

    // Constructor, Getters, and Setters

    public static class Content {
        private String contentId;
        private String userId;
        private String type;
        private String content;
        private String privacy;

        // Constructor, Getters, and Setters
    }
}