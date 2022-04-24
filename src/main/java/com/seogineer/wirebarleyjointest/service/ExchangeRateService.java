package com.seogineer.wirebarleyjointest.service;

import com.seogineer.wirebarleyjointest.common.exception.BadRequestException;
import com.seogineer.wirebarleyjointest.common.exception.SubscribeException;
import com.seogineer.wirebarleyjointest.dto.ExchangeRateResponse;
import com.seogineer.wirebarleyjointest.dto.SubscribeResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class ExchangeRateService {
    public ExchangeRateResponse getExchangeRate(String currencyCode){
        WebClient webClient = WebClient.builder().baseUrl("http://api.currencylayer.com").build();
        Optional<SubscribeResponse> subscribeResponseOptional = webClient.get()
                .uri("/live?access_key=e71a30b85dbfc4f147207e56463d5c4c&currencies=" + currencyCode + "&format=1")
                .retrieve()
                .bodyToMono(SubscribeResponse.class)
                .flux()
                .toStream()
                .findFirst();

        subscribeResponseOptional.orElseThrow(SubscribeException::new);

        if(!subscribeResponseOptional.get().getSuccess()){
            throw new BadRequestException();
        }

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        subscribeResponseOptional.ifPresent(subscribeResponse -> {
            if(!ObjectUtils.isEmpty(subscribeResponse.getQuotes().getUSDKRW())){
                exchangeRateResponse.setUSDKRW(subscribeResponse.getQuotes().getUSDKRW());
            } else if(!ObjectUtils.isEmpty(subscribeResponse.getQuotes().getUSDJPY())){
                exchangeRateResponse.setUSDJPY(subscribeResponse.getQuotes().getUSDJPY());
            } else if(!ObjectUtils.isEmpty(subscribeResponse.getQuotes().getUSDPHP())){
                exchangeRateResponse.setUSDPHP(subscribeResponse.getQuotes().getUSDPHP());
            }
        });

        return exchangeRateResponse;
    }
}
