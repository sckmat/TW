package com.yegorov.usermanagementservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/users")
public class UserController {

    @GetMapping("/")
    public Mono<ResponseEntity<Map<String, Object>>> getAllUsers(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","success");
        map.put("data",new ArrayList<>());
        return Mono.just(ResponseEntity.ok().body(map));
    }

}
