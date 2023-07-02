package ru.clevertec.news.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.clevertec.exception_handler.model.ErrorMessage;
import ru.clevertec.news.entity.User;

@Tag(name = "Authorization", description = "The Authorization API")
public interface AuthorizationOpenAPI {

    @Operation(
            operationId = "createUser",
            summary = "Method of user creation",
            requestBody = @RequestBody(
                    description = "RequestBody for User",
                    content = @Content(
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("""
                                      {
                                      "id": 2,
                                      "username": "Ilia",
                                      "password": "8888",
                                      "active": "true",
                                      "dateOfRegistry": "2023-06-18T17:34:45.426Z",
                                      "roles": [
                                         "ADMIN"
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
                                      "id": 2,
                                      "username": "Ilia",
                                      "password": "8888",
                                      "active": "true",
                                      "dateOfRegistry": "2023-06-18T17:34:45.426Z",
                                      "roles": [
                                         "ADMIN"
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
    public ResponseEntity<User> registryUser(@RequestBody User user);

}
