package ru.clevertec.news.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ru.clevertec.news.entity.Comment;
import ru.clevertec.exception_handler.model.ErrorMessage;

@Tag(name = "Comment", description = "The Comment API")
public interface CommentOpenAPI {

    @Operation(
            operationId = "getAllComments",
            summary = "Method of getting a list of comments",
            parameters = @Parameter(
                    name = "pageable",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = Pageable.class),
                    examples = @ExampleObject("""
                                    {
                                      "page": 0,
                                      "size": 1,
                                      "sort": "id,desc"
                                    }
                                    """
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "Successful response with a list of comments",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Comment.class),
                            examples = @ExampleObject("""
                                    {
                                          "id": 4,
                                          "username": "Ivan",
                                          "text": "New Text",
                                          "createDateComment": "2023-06-18T20:28:25.840Z",
                                          "updateDateComment": "2023-06-18T20:28:25.840Z",
                                          "news": {
                                            "id": 20,
                                            "title": "New Title",
                                            "text": "New Text",
                                            "createDateNews": "2023-06-18T20:28:25.841Z",
                                            "updateDateNews": "2023-06-18T20:28:25.841Z",
                                            "comments": [
                                              "string"
                                            ]
                                          }
                                    }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found comments",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "Comments not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Page<Comment>> getAllComments(Pageable pageable);

    @Operation(
            operationId = "getCommentById",
            summary = "Method of receiving comment by the id",
            parameters = @Parameter(
                    name = "id",
                    required = true,
                    description = "Enter id here",
                    example = "4"
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "Successful response with founded comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Comment.class),
                            examples = @ExampleObject("""
                                    {
                                          "id": 4,
                                          "username": "Ivan",
                                          "text": "New Text",
                                          "createDateComment": "2023-06-18T20:28:25.840Z",
                                          "updateDateComment": "2023-06-18T20:28:25.840Z",
                                          "news": {
                                            "id": 20,
                                            "title": "New Title",
                                            "text": "New Text",
                                            "createDateNews": "2023-06-18T20:28:25.841Z",
                                            "updateDateNews": "2023-06-18T20:28:25.841Z",
                                            "comments": [
                                              "string"
                                            ]
                                          }
                                    }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "Comment with id - 4 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id);

    @Operation(
            operationId = "createComment",
            summary = "Method of comment creation",
            requestBody = @RequestBody(
                    description = "RequestBody for Comment",
                    content = @Content(
                            schema = @Schema(implementation = Comment.class),
                            examples = @ExampleObject("""
                                    {
                                          "id": 4,
                                          "username": "Ivan",
                                          "text": "New Text",
                                          "createDateComment": "2023-06-18T20:28:25.840Z",
                                          "updateDateComment": "2023-06-18T20:28:25.840Z",
                                          "news": {
                                            "id": 20,
                                            "title": "New Title",
                                            "text": "New Text",
                                            "createDateNews": "2023-06-18T20:28:25.841Z",
                                            "updateDateNews": "2023-06-18T20:28:25.841Z",
                                            "comments": [
                                              "string"
                                            ]
                                          }
                                    }"""
                            )
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful response with the created comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Comment.class),
                            examples = @ExampleObject("""
                                    {
                                          "id": 4,
                                          "username": "Ivan",
                                          "text": "New Text",
                                          "createDateComment": "2023-06-18T20:28:25.840Z",
                                          "updateDateComment": "2023-06-18T20:28:25.840Z",
                                          "news": {
                                            "id": 20,
                                            "title": "New Title",
                                            "text": "New Text",
                                            "createDateNews": "2023-06-18T20:28:25.841Z",
                                            "updateDateNews": "2023-06-18T20:28:25.841Z",
                                            "comments": [
                                              "string"
                                            ]
                                          }
                                    }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "The server encountered an unexpected error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 500,
                                      "message": "Error with Insert comment",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment);

    @Operation(
            operationId = "updateComment",
            summary = "Method of comment updating",
            requestBody = @RequestBody(
                    description = "RequestBody for Comment",
                    content = @Content(
                            schema = @Schema(implementation = Comment.class),
                            examples = @ExampleObject("""
                                    {
                                          "id": 4,
                                          "username": "Ivan",
                                          "text": "New Text",
                                          "createDateComment": "2023-06-18T20:28:25.840Z",
                                          "updateDateComment": "2023-06-18T20:28:25.840Z",
                                          "news": {
                                            "id": 20,
                                            "title": "New Title",
                                            "text": "New Text",
                                            "createDateNews": "2023-06-18T20:28:25.841Z",
                                            "updateDateNews": "2023-06-18T20:28:25.841Z",
                                            "comments": [
                                              "string"
                                            ]
                                          }
                                    }"""
                            )
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response with the updated comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Comment.class),
                            examples = @ExampleObject("""
                                    {
                                          "id": 4,
                                          "username": "Ivan",
                                          "text": "New Text",
                                          "createDateComment": "2023-06-18T20:28:25.840Z",
                                          "updateDateComment": "2023-06-18T20:28:25.840Z",
                                          "news": {
                                            "id": 20,
                                            "title": "New Title",
                                            "text": "New Text",
                                            "createDateNews": "2023-06-18T20:28:25.841Z",
                                            "updateDateNews": "2023-06-18T20:28:25.841Z",
                                            "comments": [
                                              "string"
                                            ]
                                          }
                                    }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "The server encountered an unexpected error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 500,
                                      "message": "Error with Update comment",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "Comment with id - 4 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment);

    @Operation(
            operationId = "deleteComment",
            summary = "Method of deleting comment",
            requestBody = @RequestBody(
                    description = "RequestBody for Comment",
                    content = @Content(
                            schema = @Schema(implementation = Comment.class),
                            examples = @ExampleObject("""
                                    {
                                          "id": 4,
                                          "username": "Ivan",
                                          "text": "New Text",
                                          "createDateComment": "2023-06-18T20:28:25.840Z",
                                          "updateDateComment": "2023-06-18T20:28:25.840Z",
                                          "news": {
                                            "id": 20,
                                            "title": "New Title",
                                            "text": "New Text",
                                            "createDateNews": "2023-06-18T20:28:25.841Z",
                                            "updateDateNews": "2023-06-18T20:28:25.841Z",
                                            "comments": [
                                              "string"
                                            ]
                                          }
                                    }"""
                            )
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "Successful response with deleted comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Comment.class),
                            examples = @ExampleObject("""
                                    {
                                          "id": 4,
                                          "username": "Ivan",
                                          "text": "New Text",
                                          "createDateComment": "2023-06-18T20:28:25.840Z",
                                          "updateDateComment": "2023-06-18T20:28:25.840Z",
                                          "news": {
                                            "id": 20,
                                            "title": "New Title",
                                            "text": "New Text",
                                            "createDateNews": "2023-06-18T20:28:25.841Z",
                                            "updateDateNews": "2023-06-18T20:28:25.841Z",
                                            "comments": [
                                              "string"
                                            ]
                                          }
                                    }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found comment",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "Comment with id - 4 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Comment> deleteComment(@RequestBody Comment comment);
}
