package com.seogineer.wirebarleyjointest.service;

import com.seogineer.wirebarleyjointest.common.exception.SubscribeException;
import com.seogineer.wirebarleyjointest.domain.Currency;
import com.seogineer.wirebarleyjointest.domain.CurrencyRepository;
import com.seogineer.wirebarleyjointest.dto.ExchangeRateResponse;
import com.seogineer.wirebarleyjointest.dto.SubscribeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyService {
    private CurrencyRepository currencyRepository;

    public List<Currency> getCurrencyList(){
        return currencyRepository.findAll();
    }
}
