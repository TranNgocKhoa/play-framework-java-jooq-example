package com.khoa.play.controllers;

import com.khoa.play.models.AuthorDTO;
import com.khoa.play.services.AuthorService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class AuthorController extends Controller {

    private AuthorService authorService;

    @Inject
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Result getListAuthor() {
        return ok(Json.toJson(authorService.getListAuthor()));
    }

    public Result createAuthor() {
        AuthorDTO author = Json.fromJson(request().body().asJson(), AuthorDTO.class);
        return ok(Json.toJson(authorService.insertAuthor(author)));
    }

    public Result updateAuthor() {
        AuthorDTO authorDTO = Json.fromJson(request().body().asJson(), AuthorDTO.class);
        return ok(Json.toJson(authorService.updateAuthor(authorDTO)));
    }

    public Result deleteAuthor(Integer id) {
        return ok(authorService.deleteAuthor(id) ? "Deleted" : "Error");
    }
}
