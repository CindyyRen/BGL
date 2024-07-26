package com.BGL.test.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/messages")
    public String getMessage() {
        return "Hello from Docker!";
    }
}
