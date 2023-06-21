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
import ru.clevertec.news.entity.News;
import ru.clevertec.exception_handler.model.ErrorMessage;

@Tag(name = "News", description = "The News API")
public interface NewsOpenAPI {

    @Operation(
            operationId = "getAllNews",
            summary = "Method of getting a list of news",
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
                    description = "Successful response with a list of news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = News.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateNews": "2023-06-18T17:34:45.426Z",
                                        "updateDateNews": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "news": "string"
                                          }
                                        ]                         
                                      }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "News not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<Page<News>> getAllNews(Pageable pageable);

    @Operation(
            operationId = "getNewsById",
            summary = "Method of receiving news by the id",
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
                    description = "Successful response with founded news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = News.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateNews": "2023-06-18T17:34:45.426Z",
                                        "updateDateNews": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "news": "string"
                                          }
                                        ]                         
                                      }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "News with id - 20 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<News> getNewsById(@PathVariable Long id);

    @Operation(
            operationId = "createNews",
            summary = "Method of news creation",
            requestBody = @RequestBody(
                    description = "RequestBody for News",
                    content = @Content(
                            schema = @Schema(implementation = News.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateNews": "2023-06-18T17:34:45.426Z",
                                        "updateDateNews": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "news": "string"
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
                    description = "Successful response with the created news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = News.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateNews": "2023-06-18T17:34:45.426Z",
                                        "updateDateNews": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "news": "string"
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
                                      "message": "Error with Insert news",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<News> createNews(@RequestBody News news);

    @Operation(
            operationId = "updateNews",
            summary = "Method of news updating",
            requestBody = @RequestBody(
                    description = "RequestBody for News",
                    content = @Content(
                            schema = @Schema(implementation = News.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateNews": "2023-06-18T17:34:45.426Z",
                                        "updateDateNews": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "news": "string"
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
                    description = "Successful response with the updated news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = News.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateNews": "2023-06-18T17:34:45.426Z",
                                        "updateDateNews": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "news": "string"
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
                                      "message": "Error with Update news",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "News with id - 20 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<News> updateNews(@RequestBody News news);

    @Operation(
            operationId = "deleteNews",
            summary = "Method of deleting news by the id",
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
                    description = "Successful response with deleted news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = News.class),
                            examples = @ExampleObject("""
                                      {
                                        "id": 20,
                                        "title": "New Title",
                                        "text": "New Text",
                                        "createDateNews": "2023-06-18T17:34:45.426Z",
                                        "updateDateNews": "2023-06-18T17:34:45.426Z",
                                        "comments": [
                                          {
                                            "id": 4,
                                            "username": "Ivan",
                                            "text": "New Text",
                                            "createDateComment": "2023-06-18T17:34:45.426Z",
                                            "updateDateComment": "2023-06-18T17:34:45.426Z",
                                            "news": "string"
                                          }
                                        ]                         
                                      }"""
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The server not found news",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class),
                            examples = @ExampleObject("""
                                    {
                                      "status": 404,
                                      "message": "News with id - 20 not found",
                                      "time": "2023-06-18T18:47:43.225Z"
                                    }
                                    """
                            )
                    )
            )
    })
    public ResponseEntity<News> deleteNews(@PathVariable Long id);
}
