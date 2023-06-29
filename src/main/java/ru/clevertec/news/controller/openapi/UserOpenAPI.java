package ru.clevertec.news.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ru.clevertec.exception_handler.model.ErrorMessage;
import ru.clevertec.news.entity.User;

@Tag(name = "User", description = "The User API")
public interface UserOpenAPI {

    @Operation(
            operationId = "getAllUser",
            summary = "Method of getting a list of users",
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
                    description = "Successful response with a list of users",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateUser": "2023-06-18T17:34:45.426Z",
                                        "updateDateUser": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "user": "string"
                                          }
                                        ]                         
                                      }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "User not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Page<User>> getAllUser(Pageable pageable);

    @Operation(
            operationId = "getUserById",
            summary = "Method of receiving user by the id",
            parameters = @Parameter(
                    name = "id",
                    required = true,
                    description = "Enter id here",
                    example = "20"
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "Successful response with founded user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateUser": "2023-06-18T17:34:45.426Z",
                                        "updateDateUser": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "user": "string"
                                          }
                                        ]                         
                                      }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "User with id - 20 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<User> getUserById(@PathVariable Long id);

    @Operation(
            operationId = "createUser",
            summary = "Method of user creation",
            requestBody = @RequestBody(
                    description = "RequestBody for User",
                    content = @Content(
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateUser": "2023-06-18T17:34:45.426Z",
                                        "updateDateUser": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "user": "string"
                                          }
                                        ]                         
                                      }"""
                            )
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Successful response with the created user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateUser": "2023-06-18T17:34:45.426Z",
                                        "updateDateUser": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "user": "string"
                                          }
                                        ]                         
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
                                      "message": "Error with Insert user",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<User> createUser(@RequestBody User user);

    @Operation(
            operationId = "updateUser",
            summary = "Method of user updating",
            requestBody = @RequestBody(
                    description = "RequestBody for User",
                    content = @Content(
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateUser": "2023-06-18T17:34:45.426Z",
                                        "updateDateUser": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "user": "string"
                                          }
                                        ]                         
                                      }"""
                            )
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response with the updated user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateUser": "2023-06-18T17:34:45.426Z",
                                        "updateDateUser": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "user": "string"
                                          }
                                        ]                         
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
                                      "message": "Error with Update user",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "User with id - 20 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<User> updateUser(@RequestBody User user);

    @Operation(
            operationId = "deleteUser",
            summary = "Method of deleting user by the id",
            parameters = @Parameter(
                    name = "id",
                    required = true,
                    description = "Enter id here",
                    example = "20"
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "Successful response with deleted user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateUser": "2023-06-18T17:34:45.426Z",
                                        "updateDateUser": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "user": "string"
                                          }
                                        ]                         
                                      }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "User with id - 20 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<User> deleteUser(@PathVariable Long id);
}
