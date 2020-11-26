package com.currency.currencyservice.service.config;

import com.currency.currencyservice.service.CurrencyService;
import com.currency.currencyservice.service.CurrencyServiceSimpleImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyServiceConfig {

    @Bean
    protected CurrencyService simpleCurrencyService() {
        return new CurrencyServiceSimpleImpl();
    }
}
