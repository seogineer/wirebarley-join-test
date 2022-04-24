package com.seogineer.wirebarleyjointest.service;

import com.seogineer.wirebarleyjointest.common.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExchangeRateServiceTest {
    @Autowired
    ExchangeRateService exchangeRateService;

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
