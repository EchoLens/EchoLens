package models;
import lombok.Data;

@Data
public class SearchResponse {
    private java.util.List<ContentListResponse.Content> searchResults;

    // Constructor, Getters, and Setters
}