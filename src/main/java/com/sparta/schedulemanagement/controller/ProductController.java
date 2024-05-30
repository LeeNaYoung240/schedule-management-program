package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<String> getProducts(HttpServletRequest req) {
        String message = "ProductController.getProducts : 인증 완료";
        System.out.println(message);

        User user = (User) req.getAttribute("user");
        System.out.println("user.getUsername() = " + user.getUsername());

        String responseBody = message + "\nuser.getUsername() = " + user.getUsername();

        return ResponseEntity.ok(responseBody);
    }
}