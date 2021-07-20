package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.component.DollarCalculator;
import com.example.springcalculator.component.MarketApi;
import com.example.springcalculator.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// 컨트롤러 테스트 시에 사용
@WebMvcTest(CalculatorController.class)
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired //Mvc를 Mock으로 테스트 하겠다.
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void sumTest() throws Exception {
        // http://localhost:8080/api/sum
        mockMvc.perform( // 해당 url로 get 시작
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x","10")
                        .queryParam("y","10")
        ).andExpect( // status 는 ok 원하고
                MockMvcResultMatchers.status().isOk()
        ).andExpect( // content는 60000 원함
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print()); // 내용 출력
    }

    @Test
    public void minusTest() throws Exception {
        Req req = new Req();
        req.setX(10);
        req.setY(10);

        // 객체를 json으로 변환
        String json = new ObjectMapper().writeValueAsString(req);


        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                // result 에 접근
                MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(MockMvcResultHandlers.print());
    }


}
