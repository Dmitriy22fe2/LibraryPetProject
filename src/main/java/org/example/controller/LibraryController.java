package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {
    @GetMapping()
    public String main() {
        return "/main";
    }
}
