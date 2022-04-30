package com.seogineer.wirebarleyjointest.controller;


import com.seogineer.wirebarleyjointest.common.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeRateControllerTest {
    @Autowired
    ExchangeRateController exchangeRateController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(exchangeRateController)
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .alwaysDo(print())
                .build();
    }

    @Test
    public void PHP_환율_요청하기() throws Exception {
        mockMvc.perform(
                get("/exchange/PHP")
        ).andExpect(status().isOk());
    }

    @Test
    public void KRW_환율_요청하기() throws Exception {
        mockMvc.perform(
                get("/exchange/KRW")
        ).andExpect(status().isOk());
    }

    @Test
    public void JPY_환율_요청하기() throws Exception {
        mockMvc.perform(
                get("/exchange/JPY")
        ).andExpect(status().isOk());
    }

    @Test
    public void 잘못된_환율_요청하기() {
        Exception exception = assertThrows(BadRequestException.class, () -> {
            exchangeRateController.getExchangeRate("QQQ");
        });

        String expectedMessage = "THE REQUEST IS NOT VALID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
