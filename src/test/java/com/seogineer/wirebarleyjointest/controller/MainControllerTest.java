package com.seogineer.wirebarleyjointest.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MainControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인_페이지_로딩(){
        String body = this.restTemplate.getForObject("/", String.class);

        assertThat(body).contains("환율 계산");
    }
}
