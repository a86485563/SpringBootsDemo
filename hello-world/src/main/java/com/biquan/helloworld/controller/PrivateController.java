package com.biquan.helloworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/private")
public class PrivateController {

	@CrossOrigin("http://localhost:8080")
    @PostMapping("/9090")
    public String getMessage(HttpServletRequest request) {
		System.out.println("Hello from 9090 private API controller");
        return "Hello from 9090 private API controller";
    }
}