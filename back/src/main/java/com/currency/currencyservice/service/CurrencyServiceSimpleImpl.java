package com.currency.currencyservice.service;

import com.currency.currencyservice.model.dao.CurrencyDao;
import com.currency.currencyservice.model.entity.Currency;
import com.currency.currencyservice.service.exception.CurrencyNotFoundException;

import java.util.List;
import java.util.Optional;

public class CurrencyServiceSimpleImpl implements CurrencyService {

    private static CurrencyDao currencyDao;

    public CurrencyServiceSimpleImpl() {
        currencyDao = new CurrencyDao();
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyDao.getAll();
    }

    @Override
    public Currency getCurrencyValueByCurrencyCode(String code) throws CurrencyNotFoundException {
        return currencyDao.get(code)
                .orElseThrow(() -> new CurrencyNotFoundException(code));
    }

    @Override
    public Currency addCurrency(Currency currency) {
        if (!currencyDao.get(currency.getName()).isPresent()) {
            return currencyDao.save(currency);
        }
        return currency;
    }

    @Override
    public Currency deleteCurrency(String name) {
        return currencyDao.delete(name);
    }
}