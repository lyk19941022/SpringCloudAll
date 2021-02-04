package com.liuyukun.userconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RestServer {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "back")
    public String alive(){
        String url = "http://user-provider/user/alive";
        String str = restTemplate.getForObject(url, String.class);
        return str;
    }

    public String back(){
        return "呵呵";
    }
}
