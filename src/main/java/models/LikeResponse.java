package models;
import lombok.Data;

@Data
public class LikeResponse {
    private String contentId;
    private int likesCount;
    private boolean likedByUser;

    // Constructor, Getters, and Setters
}