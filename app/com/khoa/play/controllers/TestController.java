package com.khoa.play.controllers;

import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestController extends Controller {
    @Inject
    public TestController() {

    }

    public Result test() {
        return ok("It works");
    }
}
