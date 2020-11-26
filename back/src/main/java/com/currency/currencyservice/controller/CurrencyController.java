package com.currency.currencyservice.controller;

import com.currency.currencyservice.model.entity.Currency;
import com.currency.currencyservice.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @CrossOrigin
    @GetMapping("/{code}")
    @ResponseBody
    public Currency getCurrencyValue(@PathVariable String code) {
        return currencyService.getCurrencyValueByCurrencyCode(code);
    }

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public List<Currency> getCurrencyValue() {
        return currencyService.getAllCurrencies();
    }

    @CrossOrigin
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Currency addCurrencyValue(@RequestBody Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{code}")
    @ResponseBody
    public Currency deleteCurrency(@PathVariable String code) {
        return currencyService.deleteCurrency(code);
    }
}