package com.seogineer.wirebarleyjointest.controller;

import com.seogineer.wirebarleyjointest.domain.Validator;
import com.seogineer.wirebarleyjointest.dto.ExchangeRateResponse;
import com.seogineer.wirebarleyjointest.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExchangeRateController {
    private ExchangeRateService exchangeRateService;
    private Validator validator;

    @GetMapping("/exchange/{currencyCode}")
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(@PathVariable String currencyCode){
        validator.validate(currencyCode);
        return ResponseEntity.ok(exchangeRateService.getExchangeRate(currencyCode));
    }
}
