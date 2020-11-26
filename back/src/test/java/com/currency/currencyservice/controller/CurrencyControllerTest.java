package com.currency.currencyservice.controller;

import com.currency.currencyservice.model.entity.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_client_ok_when_correct_path_as_currency_code_given() throws Exception {
        mockMvc.perform(get("/currency/EUR"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_client_ok_when_add_currency_with_correct_path() throws Exception {
        Currency currency = new Currency("Test", 420.0);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson=ow.writeValueAsString(currency);
//
//        mockMvc.perform(post("/currency/add").contentType(APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/currency/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(currency)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_client_ok_when_correct_path_for_all_currencies() throws Exception {
        mockMvc.perform(get("/currency/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"xaz", "CHF", "ADASDcc"})
    public void should_return_client_bad_request_when_wrong_path_as_currency_code_given(String currency) throws Exception {
        mockMvc.perform(get("/currency/" + currency))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}