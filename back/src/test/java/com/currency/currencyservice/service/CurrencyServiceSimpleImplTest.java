package com.currency.currencyservice.service;

import java.util.stream.Stream;

import com.currency.currencyservice.model.entity.Currency;
import com.currency.currencyservice.service.exception.CurrencyNotFoundException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrencyServiceSimpleImplTest {

    final CurrencyService simpleCurrencyService = new CurrencyServiceSimpleImpl();

    private static Stream<Arguments> currencyValuesProvider() {
        return Stream.of(
                Arguments.of("EUR", 4.55),
                Arguments.of("PLN", 1.00)
        );
    }

    @ParameterizedTest(name = "{index} => givenCurrencyCode={0}, expectedCurrencyValue={1}}")
    @MethodSource("currencyValuesProvider")
    void should_return_correct_currency_value_when_currency_code_given(
            String givenCurrencyCode,
            Double expectedCurrencyValue
    ) {
        Currency response = simpleCurrencyService.getCurrencyValueByCurrencyCode(givenCurrencyCode);

        assertEquals(expectedCurrencyValue, response.getCurrencyValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"xaz", "CHF", "", "ADASDcc"})
    void should_throw_CurrencyNotFoundException_when_wrong_currency_code_given(String currencyCode) {
        assertThrows(CurrencyNotFoundException.class, () -> simpleCurrencyService.getCurrencyValueByCurrencyCode(currencyCode));
    }
}