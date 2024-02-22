package com.projects.echoLens.controllers;

import com.projects.echoLens.dtos.RegisterRequestV1DTO;
import com.projects.echoLens.dtos.ResponseV1DTO;
import com.projects.echoLens.enums.StatusEnum;
import com.projects.echoLens.services.UserServiceV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.inject.Named;
import javax.inject.Singleton;

import jakarta.validation.Valid;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.projects.echoLens.EchoLensConstants.ECHOLENS_VI_ROOT;
import static com.projects.echoLens.EchoLensConstants.REGISTER_PATH;
import static com.projects.echoLens.Spring.ErrorCodes.APPLICATION_EXEC_CODE;
import static com.projects.echoLens.Spring.ErrorCodes.APPLICATION_EXEC_MESSAGE;

@RestController
@Named
@Singleton
@RequestMapping(ECHOLENS_VI_ROOT)
public class EchoLensController {

    @Autowired
    private UserServiceV1 userServiceV1;

    @Operation(summary = "Register a new user",
            description = "Register a new user with username, email, and password.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    @PostMapping(REGISTER_PATH)
    public ResponseEntity<ResponseV1DTO> registerUser(@Parameter(name = "userRegisterRequest", required = true) @Valid
                                                         @RequestBody final RegisterRequestV1DTO registerRequest) {
        // Logic to handle user registration
        ResponseV1DTO response;
        try {
            response = userServiceV1.userRegister(registerRequest);

            // 假设成功注册用户会返回特定的成功代码
            if (response.getCode().equals(StatusEnum.valueOf("SUCCESS"))) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response = new ResponseV1DTO();
            response.setCode(APPLICATION_EXEC_CODE);
            response.setMessage(APPLICATION_EXEC_MESSAGE);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate a user",
            description = "Logs in a user and returns a token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Operation",
                            content = @Content(schema = @Schema(implementation = TokenResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            })
    public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        // Logic to authenticate user
        return ResponseEntity.ok(new TokenResponse(/* token */));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logoutUser() {
        // Logic to handle user logout
        return ResponseEntity.ok(new MessageResponse("Logged out successfully."));
    }

    @PostMapping("/upload")
    public ResponseEntity<ContentResponse> uploadContent(@RequestBody ContentRequest contentRequest) {
        // Logic to handle content upload
        return ResponseEntity.ok(new ContentResponse(/* contentId, message */));
    }

    @GetMapping("/content")
    public ResponseEntity<ContentListResponse> viewContent(@RequestParam String privacy) {
        // Logic to retrieve content based on privacy settings
        return ResponseEntity.ok(new ContentListResponse(/* List of content */));
    }

    @PutMapping("/content/{contentId}/privacy")
    public ResponseEntity<MessageResponse> updateContentPrivacy(@PathVariable String contentId, @RequestBody PrivacyUpdateRequest privacyUpdateRequest) {
        // Logic to update content privacy
        return ResponseEntity.ok(new MessageResponse("Privacy updated successfully."));
    }

    @GetMapping("/content/user/{userId}")
    public ResponseEntity<ContentListResponse> getUserContent(@PathVariable String userId, @RequestParam(required = false) String privacy) {
        // Logic to retrieve user-specific content
        return ResponseEntity.ok(new ContentListResponse(/* List of user content */));
    }

    @DeleteMapping("/content/{contentId}")
    public ResponseEntity<MessageResponse> deleteContent(@PathVariable String contentId) {
        // Logic to delete content
        return ResponseEntity.ok(new MessageResponse("Content deleted successfully."));
    }

    @PostMapping("/content/{contentId}/like")
    public ResponseEntity<LikeResponse> likeContent(@PathVariable String contentId, @RequestBody LikeRequest likeRequest) {
        // Logic to like a content
        return ResponseEntity.ok(new LikeResponse(/* contentId, likesCount, likedByUser */));
    }

    @PostMapping("/content/{contentId}/comment")
    public ResponseEntity<CommentResponse> commentOnContent(@PathVariable String contentId, @RequestBody CommentRequest commentRequest) {
        // Logic to comment on content
        return ResponseEntity.ok(new CommentResponse(/* commentId, userId, contentId, comment, timestamp */));
    }

    @GetMapping("/friends/{userId}")
    public ResponseEntity<FriendsListResponse> getFriendList(@PathVariable String userId) {
        // Logic to retrieve friend list
        return ResponseEntity.ok(new FriendsListResponse(/* List of friends */));
    }

    @GetMapping("/content/{contentId}/permission/{userId}")
    public ResponseEntity<PermissionResponse> checkPermission(@PathVariable String contentId, @PathVariable String userId) {
        // Logic to check permission
        return ResponseEntity.ok(new PermissionResponse(/* permission */));
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> searchContent(@RequestParam String query, @RequestParam(required = false) String sortBy, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        // Logic for content search and sorting
        return ResponseEntity.ok(new SearchResponse(/* List of search results */));
    }
}
