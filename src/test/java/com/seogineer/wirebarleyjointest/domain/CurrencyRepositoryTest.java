package com.seogineer.wirebarleyjointest.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CurrencyRepositoryTest {
    @Autowired
    CurrencyRepository currencyRepository;

    @Test
    public void 통화코드_읽어오기(){
        List<Currency> currencyList = currencyRepository.findAll();

        Currency currency = currencyList.get(0);
        assertEquals("한국", currency.getCountryName());
        assertEquals("KRW", currency.getCurrencyCode());
    }
}
