package com.seogineer.wirebarleyjointest.service;

import com.seogineer.wirebarleyjointest.common.exception.BadRequestException;
import com.seogineer.wirebarleyjointest.common.exception.SubscribeException;
import com.seogineer.wirebarleyjointest.dto.ExchangeRateResponse;
import com.seogineer.wirebarleyjointest.dto.SubscribeResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

@Service
@AllArgsConstructor
public class ExchangeRateService {
    private WebClient webClient;

    public Mono<ExchangeRateResponse> getExchangeRate(String currencyCode){
        Mono<SubscribeResponse> subscribeResponseMono = subscribeFromCurrencyLayer(currencyCode);

        return subscribeResponseMono.flatMap(subscribeResponse -> {
            if(!subscribeResponse.getSuccess()){
                return Mono.error(new SubscribeException());
            }

            ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();

            if(subscribeResponse.getQuotes().getUSDKRW() != null){
                exchangeRateResponse.setUSDKRW(
                        new BigDecimal(
                                subscribeResponse.getQuotes().getUSDKRW())
                                .setScale(2, RoundingMode.HALF_EVEN)
                                .floatValue());
            } else if(subscribeResponse.getQuotes().getUSDJPY() != null){
                exchangeRateResponse.setUSDJPY(
                        new BigDecimal(
                                subscribeResponse.getQuotes().getUSDJPY())
                                .setScale(2, RoundingMode.HALF_EVEN)
                                .floatValue());
            } else if(subscribeResponse.getQuotes().getUSDPHP() != null){
                exchangeRateResponse.setUSDPHP(
                        new BigDecimal(
                                subscribeResponse.getQuotes().getUSDPHP())
                                .setScale(2, RoundingMode.HALF_EVEN)
                                .floatValue());
            }

            return Mono.just(exchangeRateResponse);
        });
    }

    private Mono<SubscribeResponse> subscribeFromCurrencyLayer(String currencyCode){
        return webClient.mutate()
                    .baseUrl("http://api.currencylayer.com")
                    .build()
                    .get()
                    .uri("/live?access_key=e71a30b85dbfc4f147207e56463d5c4c&format=1&&currencies={currencyCode}", currencyCode)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new BadRequestException()))
                    .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new SubscribeException()))
                    .bodyToMono(SubscribeResponse.class)
                    .timeout(Duration.ofSeconds(20));
    }
}
