package com.liuyukun.eurekaserver;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    DiscoveryClient client;

    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient lb;


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
        //通过服务名，找列表
        List<InstanceInfo> instances = client2.getInstancesByVipAddress("provider", false);
        for (InstanceInfo ins : instances){
            System.out.println(ins);
        }
        if(instances.size() > 0){
            InstanceInfo instance = instances.get(0);
            if(instance.getStatus() == InstanceInfo.InstanceStatus.UP){
                String url = "http://" + instance.getHostName() + ":" + instance.getPort() + "/getHi";
                System.out.println("url" + url);

                //通过restTemplate调用其他服务接口
                RestTemplate restTemplate = new RestTemplate();
                String respStr = restTemplate.getForObject(url, String.class);
                System.out.println("respStr：" + respStr);
            }
        }
        return "xxoo";
    }

    @GetMapping("/client5")
    public Object client5(){
        //通过ribbon完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance instance = lb.choose("provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";

        //通过restTemplate调用其他服务接口
        RestTemplate restTemplate = new RestTemplate();
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println("respStr：" + respStr);
        return "xxoo";
    }
}
