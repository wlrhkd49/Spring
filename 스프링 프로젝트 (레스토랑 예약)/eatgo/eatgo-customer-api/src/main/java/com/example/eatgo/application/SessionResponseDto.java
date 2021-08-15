package com.example.eatgo.application;

import lombok.Builder;
import lombok.Data;

// accessToken 데이터를 위한 클래스
@Data
@Builder
public class SessionResponseDto {

    private String accessToken;
}
