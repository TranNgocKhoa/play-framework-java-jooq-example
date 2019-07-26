package com.khoa.play.controllers;

import com.khoa.play.models.AuthorDTO;
import com.khoa.play.services.AsyncAuthorService;
//import io.swagger.annotations.Api;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

//@Api
public class AsyncAuthorController extends Controller {

    private AsyncAuthorService authorService;

    private HttpExecutionContext httpExecutionContext;

    @Inject
    public AsyncAuthorController(AsyncAuthorService authorService, HttpExecutionContext httpExecutionContext) {
        this.authorService = authorService;
        this.httpExecutionContext = httpExecutionContext;
    }

    public CompletionStage<Result> getListAuthor() {
        return authorService.getListAuthor().thenApply(result -> ok(Json.toJson(result)));
    }

    public CompletionStage<Result> getListAuthor2() {
        return authorService.getListAuthor2().thenApply(result -> ok(Json.toJson(result)));
    }

    public CompletionStage<Result> insertAuthor() {
        AuthorDTO authorDTO = Json.fromJson(request().body().asJson(), AuthorDTO.class);
        return authorService.insertAuthorTx(authorDTO).thenApply(result -> ok("Inserted"));
    }
}
