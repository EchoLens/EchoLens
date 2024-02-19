package models;
import lombok.Data;

@Data
public class CommentResponse {
    private String commentId;
    private String userId;
    private String contentId;
    private String comment;
    private java.time.LocalDateTime timestamp;

    // Constructor, Getters, and Setters
}