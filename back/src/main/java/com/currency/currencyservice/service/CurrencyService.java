package com.currency.currencyservice.service;

import com.currency.currencyservice.model.entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrencies();

    Currency getCurrencyValueByCurrencyCode(String code);

    Currency addCurrency(Currency currency);

    Currency deleteCurrency(String name);
}
