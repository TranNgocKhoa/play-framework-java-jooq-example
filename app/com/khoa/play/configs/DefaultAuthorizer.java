package com.khoa.play.configs;

import com.khoa.play.utils.JwtsUtil;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;
import play.Logger;

import javax.inject.Inject;
import java.util.Optional;

public class DefaultAuthorizer extends Security.Authenticator {

    private static final String TOKEN_KEY = "Authorization";

    private JwtsUtil jwtsUtil;


    @Inject
    public DefaultAuthorizer(JwtsUtil jwtsUtil) {
        this.jwtsUtil = jwtsUtil;
    }

    @Override
    public String getUsername(Http.Context ctx) {
        // Do Authentication here
        // Returning NULL means request in not authorized
        Optional<String> token = ctx.request().getHeaders().get(TOKEN_KEY);
        Logger.debug(token.get());
        try {
            return jwtsUtil.getClaims(token.get().substring(7));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        // Response for unauthenticated request
        Exception exception = new Exception("Unauthorized");
        return Results.unauthorized(Json.toJson(exception.getMessage()));
    }

    private String getToken(Http.Request request) {
        return request.getHeaders().get(TOKEN_KEY).get();
    }
}