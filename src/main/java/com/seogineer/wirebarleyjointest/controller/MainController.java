package com.seogineer.wirebarleyjointest.controller;

import com.seogineer.wirebarleyjointest.domain.Currency;
import com.seogineer.wirebarleyjointest.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    private CurrencyService currencyService;

    @GetMapping("/")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("index");

        List<Currency> currencyList = currencyService.getCurrencyList();
        modelAndView.addObject("currencyList", currencyList);

        return modelAndView;
    }
}
