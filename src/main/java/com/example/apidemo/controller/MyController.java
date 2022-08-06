package com.example.apidemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("my")
public class MyController {
    @RequestMapping("h1")
    public String h1() {
        return "OKOK";
    }
}
