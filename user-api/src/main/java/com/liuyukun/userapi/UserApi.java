package com.liuyukun.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/user")
public interface UserApi {

    @GetMapping("/alive")
    public String alive();

    @PostMapping("/postPerson")
    public Person postPerson(Person person);
}
