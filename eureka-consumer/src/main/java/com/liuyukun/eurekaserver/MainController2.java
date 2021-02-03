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
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainController2 {

    @Autowired
    DiscoveryClient client;

    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/client6")
    public Object client6(){
        //通过ribbon完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance instance = lb.choose("provider");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";

        //通过restTemplate调用其他服务接口
        RestTemplate restTemplate = new RestTemplate();
        String respStr = restTemplate.getForObject(url, String.class);
        return respStr;
    }

    /**
     * 手动负载均衡
     * @return
     */
    @GetMapping("/client7")
    public Object client7(){

        List<ServiceInstance> instances = discoveryClient.getInstances("provider");
        //System.out.println("instances" + instances);

        //自定义算法
        //随机
        int nextInt = new Random().nextInt(instances.size());
        ServiceInstance instance = instances.get(nextInt);

        //轮询
        /*AtomicInteger atomicInteger = new AtomicInteger();
        int i = atomicInteger.getAndIncrement();
        ServiceInstance instance = instances.get(i % instances.size());*/


        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";

        //通过restTemplate调用其他服务接口
        RestTemplate restTemplate = new RestTemplate();
        String respStr = restTemplate.getForObject(url, String.class);
        return respStr;
    }

    @GetMapping("/client8")
    public Object client8(){
        //通过ribbon完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance instance = lb.choose("provider");

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";

        //通过restTemplate调用其他服务接口
        RestTemplate restTemplate = new RestTemplate();
        String respStr = restTemplate.getForObject(url, String.class);
        System.out.println(respStr);
        return respStr;
    }
}
