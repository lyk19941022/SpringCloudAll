package com.liuyukun.userconsumer;

import com.liuyukun.userapi.Person;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProviderBack implements ConsumerApi{
    @Override
    public Map<Integer, String> getMap(Integer id1) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public String alive() {
        return "降级了...";
    }

    @Override
    public Person postPerson(Person person) {
        return null;
    }
}
