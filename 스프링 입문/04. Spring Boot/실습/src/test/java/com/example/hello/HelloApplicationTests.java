package com.example.hello;

import com.example.hello.dto.UserTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloApplicationTests {

    @Test
    void test() throws JsonProcessingException {
        System.out.println("--------------");

        // Text Json -> Object
        // Object -> Text Json

        // controller req json(text) -> object
        // response object -> json(text)

        var objectMapper = new ObjectMapper();

        // object -> text
        // object mapper get method 를 활용한다.
        // get을 다른곳에 활용하지 않도록 유념!!!
        var user = new UserTest("steve", 10, "010-1111-2222");
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // text -> object
        // object Mapper 는 default 생성자를 필요로 한다.
        var objectUser = objectMapper.readValue(text, UserTest.class);
        System.out.println(objectUser);
    }

}
