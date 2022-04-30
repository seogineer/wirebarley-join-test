package com.seogineer.wirebarleyjointest.service;

import com.seogineer.wirebarleyjointest.common.exception.BadRequestException;
import com.seogineer.wirebarleyjointest.dto.ExchangeRateResponse;
import com.seogineer.wirebarleyjointest.dto.SubscribeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class ExchangeRateService {
    private WebClient webClient;

    public ExchangeRateResponse getExchangeRate(String currencyCode){
        SubscribeResponse subscribeResponse = subscribeFromCurrencyLayer(currencyCode);

        if(!subscribeResponse.getSuccess()){
            throw new BadRequestException();
        }

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();

        if(!ObjectUtils.isEmpty(subscribeResponse.getQuotes().getUSDKRW())){
            exchangeRateResponse.setUSDKRW(subscribeResponse.getQuotes().getUSDKRW());
        } else if(!ObjectUtils.isEmpty(subscribeResponse.getQuotes().getUSDJPY())){
            exchangeRateResponse.setUSDJPY(subscribeResponse.getQuotes().getUSDJPY());
        } else if(!ObjectUtils.isEmpty(subscribeResponse.getQuotes().getUSDPHP())){
            exchangeRateResponse.setUSDPHP(subscribeResponse.getQuotes().getUSDPHP());
        }

        return exchangeRateResponse;
    }

    private SubscribeResponse subscribeFromCurrencyLayer(String currencyCode){
        return webClient.mutate()
                .baseUrl("http://api.currencylayer.com")
                .build()
                .get()
                .uri("/live?access_key=e71a30b85dbfc4f147207e56463d5c4c&format=1&&currencies={currencyCode}", currencyCode)
                .retrieve()
                .bodyToMono(SubscribeResponse.class)
                .block();
    }
}
