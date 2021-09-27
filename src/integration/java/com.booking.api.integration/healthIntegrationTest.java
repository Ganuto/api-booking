package com.booking.api.integration;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class healthIntegrationTest extends BookingApiApplicationIntegrationTest {

    @Test
    public void healthCheck() {
        IntegrationRequests.get("/actuator/health")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
