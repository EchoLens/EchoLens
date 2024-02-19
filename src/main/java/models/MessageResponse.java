package models;
import lombok.Data;

@Data
public class MessageResponse {
    private String message;

    // Constructor, Getters, and Setters
    public MessageResponse(String message){
        this.message = message;
    }
}