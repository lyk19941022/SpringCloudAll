package com.liuyukun.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    HealthStatusService healthStatusSrv;

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi";
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {
        //远程控制服务上下线
        healthStatusSrv.setStatus(status);
        return healthStatusSrv.getStatus();
    }

}
