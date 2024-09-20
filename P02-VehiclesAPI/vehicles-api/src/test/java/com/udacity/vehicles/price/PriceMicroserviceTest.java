package com.udacity.vehicles.price;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PriceMicroserviceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPricingService() {
            String response = restTemplate.getForObject("http://localhost:8082/price", String.class);
            System.out.println("Response: " + response);
            assertThat(response).isNotEmpty();
    }
}
