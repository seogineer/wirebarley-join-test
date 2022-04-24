package com.seogineer.wirebarleyjointest.domain;

import com.seogineer.wirebarleyjointest.common.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Validator {
    public void validate(String requestCurrencyCode){
        List<String> currencyCodes = new ArrayList<>();
        currencyCodes.add("KRW");
        currencyCodes.add("JPY");
        currencyCodes.add("PHP");

        if(!currencyCodes.contains(requestCurrencyCode)){
            throw new BadRequestException();
        }
    }
}
