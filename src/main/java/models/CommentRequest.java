package models;
import lombok.Data;

@Data
public class CommentRequest {
    private String userId;
    private String comment;

    // Getters and Setters
}