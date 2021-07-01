package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello") //http://localhost:8080/api/get/hello
    public String hello() {
        return "get Hello";
    }

    //@RequestMapping("/hi") // get, post, put, delete 모두 동작
    @RequestMapping(path = "/hi", method = RequestMethod.GET) //get
    //http://localhost:8080/api/get/hi
    public String hi() {
        return "hi";
    }

    //TODO: PathVariable
    //http://localhost:8080/api/get/path-variable/{name}
    @GetMapping("/path-variable/{id}")
    public String pathVariable(@PathVariable(name = "id") String pathName) { // 변수와 이름 다르게 설정할 때 사용
        System.out.println("PathVariable : "+pathName);
        return pathName;
    }

    //TODO: QueryParameter
    // 검색 시 매개변수 인자들 (? 이후)
    //http://localhost:8080/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = "+ entry.getValue()+"\n");
        });

        return sb.toString();
    }

    //TODO: param을 주어진 것만 지정하는 경우
    @GetMapping(path = "/query-param02")
    public String queryParam02(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam int age) {

        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " + email + " " + age;

    }

    //TODO:객체로 받는 경우
    // 이 때, RequestParam은 넣지 않음!
    @GetMapping(path = "/query-param03")
    public String queryParam03(UserRequest userRequest) {

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();

    }


}
