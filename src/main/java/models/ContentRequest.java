package models;
import lombok.Data;

@Data
public class ContentRequest {
    private String userId;
    private String type;
    private String content;
    private String privacy;

    // Getters and Setters
}