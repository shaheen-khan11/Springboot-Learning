package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.bean.Country;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTestCases {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void testIntegration_getAllCountries() throws JSONException {

        String expected = "[\r\n"
                + "  {\r\n"
                + "  \"id\":1,\r\n"
                + "  \"countryname\":\"India\",\r\n"
                + "  \"countrycapital\":\"Delhi\"\r\n"
                + "  },\r\n"
                + "  {\r\n"
                + "  \"id\":2,\r\n"
                + "  \"countryname\":\"USA\",\r\n"
                + "  \"countrycapital\":\"Washington\"\r\n"
                + "  }\r\n"
                + "]";

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    
    @Test
    @Order(2)
    void testIntegration_getcountrybyID() throws JSONException {

    	String expected = "{\r\n"
                + "  \"id\":1,\r\n"
                + "  \"countryname\":\"India\",\r\n"
                + "  \"countrycapital\":\"Delhi\"\r\n"
                + "}";

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries/1", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    
    @Test
    @Order(3)
    void testIntegration_getcountrybyname() throws JSONException {

    	String expected = "{\r\n"
                + "  \"id\":1,\r\n"
                + "  \"countryname\":\"India\",\r\n"
                + "  \"countrycapital\":\"Delhi\"\r\n"
                + "}";

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries/countryname?name=India", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    
    @Test
    @Order(4)
    void testIntegration_addcountry() throws JSONException {

    	Country country=new Country(3,"Germany","Berlin");
    	
    	String expected = "{\r\n"
                + "  \"id\":3,\r\n"
                + "  \"countryname\":\"Germany\",\r\n"
                + "  \"countrycapital\":\"Berlin\"\r\n"
                + "}";
    	
    	HttpHeaders headers=new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	HttpEntity<Country> request= new HttpEntity<Country>(country,headers);
    	
    	ResponseEntity<String> response=restTemplate.postForEntity("http://localhost:8080/addcountry", request, String.class);
    	System.out.println(response.getBody());
    	JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    
    @Test
    @Order(5)
    void testIntegration_updatecountry() throws JSONException {

    	Country country=new Country(3,"Japan","Tokyo");
    	
    	String expected = "{\r\n"
                + "  \"id\":3,\r\n"
                + "  \"countryname\":\"Japan\",\r\n"
                + "  \"countrycapital\":\"Tokyo\"\r\n"
                + "}";
    	
    	HttpHeaders headers=new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	HttpEntity<Country> request= new HttpEntity<Country>(country,headers);
    	
    	ResponseEntity<String> response=restTemplate.exchange("http://localhost:8080/updatecountry/3",HttpMethod.PUT, request, String.class);
    	System.out.println(response.getBody());
    	JSONAssert.assertEquals(expected, response.getBody(), false);
    }
    @Test
    @Order(5)
    void testIntegration_deletecountry() throws JSONException {

    	Country country=new Country(3,"Japan","Tokyo");
    	
    	String expected = "{\r\n"
                + "  \"id\":3,\r\n"
                + "  \"countryname\":\"Japan\",\r\n"
                + "  \"countrycapital\":\"Tokyo\"\r\n"
                + "}";
    	
    	HttpHeaders headers=new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	HttpEntity<Country> request= new HttpEntity<Country>(country,headers);
    	
    	ResponseEntity<String> response=restTemplate.exchange("http://localhost:8080/deletecountry/3",HttpMethod.DELETE, request, String.class);
    	System.out.println(response.getBody());
    	JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}
