package com.liuyukun.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

@RestController
public class MainController {

    @Value("${server.port}")
    String port;

    @Autowired
    HealthStatusService healthStatusSrv;

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi port:" + port;
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {
        //远程控制服务上下线
        healthStatusSrv.setStatus(status);
        return healthStatusSrv.getStatus();
    }

    @GetMapping("/getMap")
    public Map<String,String> getMap(){
        return Collections.singletonMap("id","100");
    }

    @GetMapping("/getObj")
    public Person getObj(){
        Person person = new Person(100, "xx");
        return person;
    }

    @GetMapping("/getObj2")
    public Person getObj2(String name){
        Person person = new Person(100, name);
        return person;
    }

    @PostMapping("/postLocation")
    public URI postParam(@RequestBody Person person, HttpServletResponse response) throws Exception {

        URI uri = new URI("https://www.baidu.com/s?wd=" + person.getName().trim());
        response.addHeader("Location", uri.toString());
        return uri;
    }
}
