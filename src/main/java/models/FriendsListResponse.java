package models;
import lombok.Data;

@Data
public class FriendsListResponse {
    private java.util.List<Friend> friends;

    // Constructor, Getters, and Setters

    public static class Friend {
        private String friendId;
        private String username;
        private String status;

        // Constructor, Getters, and Setters
    }
}