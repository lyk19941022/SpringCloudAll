package com.liuyukun.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    DiscoveryClient client;

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi";
    }

    @GetMapping("/client")
    public String client(){
        List<String> services = client.getServices();
        for (String s : services){
            System.out.println(s);
        }
        return "Hi";
    }

    @GetMapping("/client2")
    public Object client2(){
        return client.getInstances("provider");
    }

    @GetMapping("/client3")
    public Object client3(){
        List<ServiceInstance> provider = client.getInstances("provider");
        for (ServiceInstance ins : provider){
            System.out.println(ins);
        }
        return provider;
    }

    @GetMapping("/client4")
    public Object client4(){
        List<ServiceInstance> instances = client.getInstances("provider");
        for (ServiceInstance ins : instances){
            System.out.println(ins);
        }
        if(instances.size() > 0){
            ServiceInstance instance = instances.get(0);
        }
        return null;
    }
}
