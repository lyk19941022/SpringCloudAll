package com.liuyukun.userconsumer;

import com.liuyukun.userapi.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class MainController {

    @Autowired
    ConsumerApi api;

    @Autowired
    RestServer rest;
    @Value("${server.port}")
    String port;

    @Value("${myconfig}")
    String myconfig;

    @GetMapping("/alive")
    public String getHi(){
        return api.alive();
    }

    @GetMapping("/alive2")
    public String alive2(){
        return "consumer"+port+rest.alive();
    }


//    @Autowired
//    MashibingApi mapi;


   /* @GetMapping("/vip")
    public String vip() {

        return mapi.getVip();
    }*/

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return api.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id,String name) {
        System.out.println(id);
        return api.getMap2(id,name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso
        System.out.println(map);
        return api.getMap3(map);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso
        System.out.println(map);
        return api.postMap(map);
    }

    @GetMapping("/postPerson")
    public Person postPerson(@RequestParam Map<String,Object> map) {
        Person person = new Person();
        person.setId(Integer.parseInt(map.get("id").toString()));
        person.setName("ooxx");
        return api.postPerson(person);
    }

}
