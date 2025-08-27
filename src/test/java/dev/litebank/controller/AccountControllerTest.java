package dev.litebank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.litebank.model.PaymentMethod;
import dev.litebank.dto.requests.DepositRequest;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testCanPostDeposit() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("0123456789");
        depositRequest.setAmount(new BigDecimal(200000));
        depositRequest.setPaymentMethod(PaymentMethod.CARD);
        String json = mapper.writeValueAsString(depositRequest);
        String depositEndPoint = "/api/v1/account/deposit";
        mockMvc.perform(MockMvcRequestBuilders.post(depositEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

}
