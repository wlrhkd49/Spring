package com.example.filter.dto;

import lombok.*;

@Data
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 전체 생성자
public class User {

    private String name;
    private int age;

}
