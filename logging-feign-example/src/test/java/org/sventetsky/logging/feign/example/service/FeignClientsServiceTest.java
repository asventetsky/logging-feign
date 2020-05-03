package org.sventetsky.logging.feign.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeignClientsServiceTest {

    @Autowired
    private FeignClientsService service;

    @Test
    void invokeFeignClients_shouldLogClientsWithDifferentConfigurations() {
        service.invokeFeignClients();
    }
}