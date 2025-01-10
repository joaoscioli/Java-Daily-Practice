package org.example.demoapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    public String names() {
        return "{\"message\":\"Hello World\"}";
    }
}
