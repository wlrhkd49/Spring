package com.example.client.service;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {


    // http://localhost/api/server/hello
    // response
    public UserResponse hello() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","aaaa")
                .queryParam("age",99)
                .encode()
                .build()
                .toUri( );

        //RestTemplate로 통신
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        // getForObject 실행 순간이 client에서 server 붙는 순간
        //uri는 가져올 서버 uri, 뒤에 매개변수는 받을 타입!
        //String result = restTemplate.getForObject(uri, String.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());
        return result.getBody();
    }

    public void post() {
        // http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve") // expand는 차례대로 괄호에 매칭이 된다.
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        // 오브젝트로 만들어서 보내면 자연스럽게 json으로 바꿔줌
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        // 해당 uri 에 req을 보내서 해당 응답은 UserResponse로 받을 거야
        ResponseEntity<String> response = restTemplate.postForEntity(uri, req, String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

    }
}
