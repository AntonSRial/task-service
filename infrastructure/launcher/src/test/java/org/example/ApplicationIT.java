package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.TaskRequest;
import org.example.config.BeansConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@EnableWebMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import({ BeansConfig.class })
@SpringJUnitConfig
public class ApplicationIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper mapper = new ObjectMapper();


    private final String LOCALHOST = "http://localhost:";

    private final String API_PATH = "/price-service-rest-api/v1.0/price";


  @ParameterizedTest
  @CsvSource({"35455,1,2020-06-14T10:00:00,35.5EUR",
          "35455,1,2020-06-14T16:00:00,25.45EUR",
          "35455,1,2020-06-14T21:00:00,35.5EUR",
          "35455,1,2020-06-15T10:00:00,30.5EUR",
          "35455,1,2020-06-16T21:00:00,38.95EUR"})
  public void whenGetQueryIsExecuted_thenResultIsOkAndFinalPriceIsCorrect(String productId, String brandId, String applicationDate, String finalPrice) throws Exception {
      ResponseEntity<String> response = restTemplate.getForEntity(LOCALHOST + port + API_PATH + "?productId="+productId+"&brandId="+brandId+"&applicationDate="+applicationDate, String.class);

      JsonNode root = mapper.readTree(response.getBody());
      String price = root.get(0).get("price").asText();

      assertEquals(finalPrice, price);
      assertEquals(200, response.getStatusCodeValue());

  }
    @Test
    public void whenPostQueryIsExecuted_thenPriceIsCreated() throws Exception {
        TaskRequest request = new TaskRequest("1", new Date(), new Date(),"1","11377",0,20.23,"EUR");
        ResponseEntity<String> responsePost = restTemplate.postForEntity(LOCALHOST + port + API_PATH, new HttpEntity<>(request), String.class);

        ResponseEntity<String> responseGet = restTemplate.getForEntity(LOCALHOST + port + API_PATH + "?productId=11377&brandId=1", String.class);
        JsonNode root = mapper.readTree(responseGet.getBody());
        String price = root.get(0).get("price").asText();

        assertEquals("20.23EUR", price);
        assertEquals(200, responseGet.getStatusCodeValue());
        assertEquals(201, responsePost.getStatusCodeValue());

    }


}