package com.liuyukun.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

@RestController
public class MainController3 {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/client10")
    public Object client10(){
        String url = "http://provider/getHi";
        String respStr = restTemplate.getForObject(url, String.class);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        System.out.println("entity: "+ entity);
        System.out.println(respStr);
        return respStr;
    }

    @GetMapping("/client11")
    public Object client11(){
        String url = "http://provider/getMap";
        Map map = restTemplate.getForObject(url, Map.class);
        System.out.println(map);
        return map;
    }

    @GetMapping("/client12")
    public Object client12(){
        String url = "http://provider/getObj";
        Person person = restTemplate.getForObject(url, Person.class);
        System.out.println(person);
        return person;
    }

    @GetMapping("/client13")
    public Object client13(){
        String url = "http://provider/getObj2?name={1}";
        Person person = restTemplate.getForObject(url, Person.class,"xxx666");
        System.out.println(person);
        return person;
    }

    @GetMapping("/client14")
    public Object client14(){
        String url = "http://provider/getObj2?name={name}";
        Map<String, String> map = Collections.singletonMap("name", "x666");
        Person person = restTemplate.getForObject(url, Person.class,map);
        System.out.println(person);
        return person;
    }

    @GetMapping("/client15")
    public Object client15(HttpServletResponse response) throws IOException {
        String url ="http://provider/postLocation";

        Map<String, String> map = Collections.singletonMap("name", " memeda");
        URI location = restTemplate.postForLocation(url, map, Person.class);

        response.sendRedirect(location.toString());
        return null;
    }
}
