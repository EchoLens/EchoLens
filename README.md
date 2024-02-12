### Hi there 👋
## Echolens API Documentation

### User Authentication
#### Register
- **Description**: Register a new user.
- **URL**: `/api/v1/echolens/register`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "username": "user",
    "email": "user@example.com",
    "password": "password"
  }
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
      "userId": "123",
      "username": "freya",
      "email": "freya@icloud.com"
    }
    ```

#### Login
- **Description**: Authenticate a user.
- **URL**: `/api/v1/echolens/login`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "username": "user",
    "password": "password"
  }
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
      "token": "jwt_token_here"
    }
    ```


#### Logout
- **Description**: Log out a user.
- **URL**: `/api/v1/echolens/logout`
- **Method**: `POST`
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
      "message": "Logged out successfully."
    }
    ```

### Content Management

#### Upload Content
- **Description**: Upload new content.
- **URL**: `/api/v1/echolens/upload`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "userId": "123",
    "type": "image",
    "content": "base64_encoded_content_or_url",
    "privacy": "public"
  }
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
      "contentId": "456", "message": "Upload successful." 
    }
    ```

#### View Content
- **Description**: View content by privacy setting.
- **URL**: `/api/v1/echolens/content`
- **Method**: `GET`
- **Query Parameters**:
  - `privacy`: Specify the privacy setting (`self`, `friends`, `public`).
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    [
      {
        "contentId": "456",
        "userId": "123",
        "type": "image",
        "content": "url_to_content",
        "privacy": "public"
      }
    ]
    ```

#### Update Content Privacy
- **Description**: Update the privacy setting of content.
- **URL**: `/api/v1/echolens/content/{contentId}/privacy`
- **Method**: `PUT`
- **Body**:
  ```json
  {
    "privacy": "friends"
  }
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
      "message": "Privacy updated successfully."
    }
    ```

#### Get User Content
- **Description**: Retrieve content posted by a specific user, filtered by privacy settings.
- **URL**: `/api/content/user/{userId}`
- **Method**: `GET`
- **URL Parameters**:
  - `userId` is the ID of the user whose content is being requested.
- **Query Parameters** (optional):
  - `privacy`: Specify the privacy level of the content to retrieve (`self`, `friends`, `public`). If not specified, defaults to returning all content that the requesting user has permission to view.
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    [
      {
        "contentId": "123",
        "userId": "userId",
        "type": "image",
        "content": "url_to_content",
        "privacy": "public",
        "timestamp": "2024-02-12T15:04:05Z",
        "likesCount": 10,
        "commentsCount": 2
      },
      {
        "contentId": "456",
        "userId": "userId",
        "type": "text",
        "content": "Some text content",
        "privacy": "friends",
        "timestamp": "2024-02-13T11:30:00Z",
        "likesCount": 5,
        "commentsCount": 1
      }
    ]
    ```
- **Notes**:
  - Each item in the response array represents a piece of content posted by the user.
  - `contentId`, `userId`, `type`, `content`, and `privacy` describe the content's unique identifier, the user who posted it, the type of content (e.g., image, text, video), the content itself (or a URL to the content), and its privacy setting, respectively.
  - `timestamp` indicates when the content was posted.
  - `likesCount` and `commentsCount` provide engagement metrics for each piece of content.

- **Error Response**:
  - **Code**: 404 NOT FOUND
  - **Content**: 
    ```json
    {
      "message": "User not found"
    }
    ```
  OR
  - **Code**: 401 UNAUTHORIZED
  - **Content**: 
    ```json
    {
      "message": "Unauthorized access"
    }
    ```

#### Delete Content
- **Description**: Delete a content.
- **URL**: `/api/v1/echolens/content/{contentId}`
- **Method**: `DELETE`
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
      "message": "Content deleted successfully."
    }
    ```

### Interaction

#### Like Content
- **Description**: User likes a content. This endpoint also ensures a user can only like the content once unless they unlike it..
- **URL**: `/api/v1/echolens/content/{contentId}/like`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "userId": "123"
  }
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
        "contentId": "456",
        "likesCount": 5,
        "likedByUser": true
    }
    ```
- **Notes**:
 - **userId is the ID of the user who is liking the content.**
 - **likesCount is the total number of likes for the content after the operation.**
 - **likedByUser indicates if the action was successful and the content is now liked by the user.**

#### Comment on Content
- **Description**: Add a comment to a content.
- **URL**: `/api/v1/echolens/content/{contentId}/comment`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "userId": "123",
    "comment": "Nice post!"
  }
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
        "commentId": "789",
        "userId": "123",
        "contentId": "456",
        "comment": "Nice post!",
        "timestamp": "2024-02-12T15:04:05Z"
    }
    ```
- **Notes**:
 - **userId is the ID of the user who is making the comment.**
 - **commentId is a unique identifier for the comment.**
 - **contentId is the ID of the content being commented on.**
 - **comment is the text of the comment made by the user.**
 - **timestamp indicates when the comment was made.**

### Permission Management

#### Get Friend List
- **Description**: Retrieve the list of friends for a specific user.
- **URL**: `/api/friends/{userId}`
- **Method**: `GET`
- **URL Parameters**:
  - `userId`: The ID of the user whose friends list is being requested.
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    [
      {
        "friendId": "123",
        "username": "friendUsername1",
        "status": "confirmed"
      },
      {
        "friendId": "456",
        "username": "friendUsername2",
        "status": "confirmed"
      }
    ]
    ```

#### Check Permission
- **Description**: Internal API to verify if a user has permission to view specific content.
- **URL**: `/api/content/{contentId}/permission/{userId}`
- **Method**: `GET`
- **URL Parameters**:
  - `contentId`: The ID of the content.
  - `userId`: The ID of the user whose permission is being checked.
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    {
      "permission": true
    }
    ```
  - **Notes**:
    - Returns `true` if the user has permission to view the content, otherwise returns `false`.

### Search and Sorting

#### Search Content
- **Description**: Search for content based on query and optional sorting parameters.
- **URL**: `/api/search`
- **Method**: `GET`
- **Query Parameters**:
  - `query`: The search query string.
  - `sortBy`: (Optional) Criterion to sort the search results (`relevance`, `likes`, `comments`). Defaults to `relevance` if not specified.
  - `page`: (Optional) Page number for pagination. Defaults to 1 if not specified.
  - `size`: (Optional) Number of results per page. Defaults to 10 if not specified.
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: 
    ```json
    [
      {
        "contentId": "123",
        "userId": "userId",
        "type": "image",
        "content": "url_to_content",
        "privacy": "public",
        "timestamp": "2024-02-12T15:04:05Z",
        "likesCount": 10,
        "commentsCount": 2,
        "relevanceScore": 0.95
      },
      {
        "contentId": "456",
        "userId": "anotherUserId",
        "type": "text",
        "content": "Some text content",
        "privacy": "friends",
        "timestamp": "2024-02-13T11:30:00Z",
        "likesCount": 5,
        "commentsCount": 1,
        "relevanceScore": 0.9
      }
    ]
    ```
  - **Notes**:
    - Search results are returned as an array of content items, each with metadata including a relevance score when `sortBy` is set to `relevance`.


