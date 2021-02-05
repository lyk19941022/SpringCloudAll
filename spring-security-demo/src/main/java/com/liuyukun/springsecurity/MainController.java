package com.liuyukun.springsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("login.html")
    public String list(){
        return "login";
    }
}
