package com.khoa.play.controllers;

import com.khoa.play.models.JwtResponse;
import com.khoa.play.models.LoginDTO;
import com.khoa.play.models.UserDTO;
import com.khoa.play.services.UserService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class AuthController extends Controller {

    private UserService userService;

    @Inject
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    public CompletionStage<Result> auth() {
        LoginDTO login = Json.fromJson(request().body().asJson(), LoginDTO.class);
        return this.userService.auth(login)
                .thenApply(result -> ok(Json.toJson(result)));
    }

    public CompletionStage<Result> createUser() throws Exception {
        LoginDTO login = Json.fromJson(request().body().asJson(), LoginDTO.class);
        return this.userService.createUser(login)
                .thenApply(result -> ok(Json.toJson(result)));
    }

}
