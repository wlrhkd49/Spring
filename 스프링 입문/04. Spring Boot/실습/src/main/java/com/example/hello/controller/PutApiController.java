package com.example.hello.controller;

import com.example.hello.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put/{user_id}")
    public PutRequestDto put(@RequestBody PutRequestDto requestDto,
                             @PathVariable(name = "user_id") Long id) {
        System.out.println(id);
        return requestDto; // 바로 JSON의 데이터로 바꿔서 response 내려감
    }
}
