package com.khoa.play.controllers;

import com.khoa.play.services.AsyncAuthorService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class AsyncAuthorController extends Controller {

    private AsyncAuthorService authorService;

    @Inject
    public AsyncAuthorController(AsyncAuthorService authorService) {
        this.authorService = authorService;
    }

    public CompletionStage<Result> getListAuthor() {
        return authorService.getListAuthor().thenApply(result -> ok(Json.toJson(result)));
    }
}
