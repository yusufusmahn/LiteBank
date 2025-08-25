package dev.litebank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTransactionByAccountNumber() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String accountNumber = "0123456789";
    String json = mapper.writeValueAsString(accountNumber);
    String TransactionEndpoint = "/api/v1/transaction";
        mockMvc.perform(MockMvcRequestBuilders.get(TransactionEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
