package com.seogineer.wirebarleyjointest.service;

import com.seogineer.wirebarleyjointest.common.exception.BadRequestException;
import com.seogineer.wirebarleyjointest.dto.ExchangeRateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public class ExchangeRateServiceTest {
    @Autowired
    ExchangeRateService exchangeRateService;

    @Test
    public void 환율_요청하기(){
        ExchangeRateResponse exchangeRateResponse = exchangeRateService.getExchangeRate("KRW");
        Assertions.assertNotNull(exchangeRateResponse.getUSDKRW());
    }

    @Test
    public void 잘못된_환율_요청하기() {
        Exception exception = assertThrows(BadRequestException.class, () -> {
            exchangeRateService.getExchangeRate("QQQ");
        });

        String expectedMessage = "THE REQUEST IS NOT VALID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
