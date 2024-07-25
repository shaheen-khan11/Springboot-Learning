package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.bean.Country;
import com.example.demo.controller.CountryController;
import com.example.demo.services.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.example.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { ControllerMockMVCTest.class })
public class ControllerMockMVCTest {

    private MockMvc mockMvc;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    @Order(1)
    public void test_getAllCountries() throws Exception {
        List<Country> myCountries = new ArrayList<>();
        myCountries.add(new Country(1, "India", "Delhi"));
        myCountries.add(new Country(2, "USA", "Washington"));

        when(countryService.getAllCountries()).thenReturn(myCountries);

        this.mockMvc.perform(get("/getcountries"))
        .andExpect(status().isOk())
            .andDo(print());

        assertEquals(2, countryController.getCountries().size());
    }
    
    @Test
	@Order(2)
	public void test_getcountrybyId() throws Exception {
	Country country= new Country(3,"Germany","Berlin");
	int countryId=3;
	   
	   when(countryService.getcountrybyID(countryId)).thenReturn(country);
	   this.mockMvc.perform(get("/getcountries/{id}",countryId))
       .andExpect(status().isOk())
       .andExpect(MockMvcResultMatchers.jsonPath(".id").value(3))
       .andExpect(MockMvcResultMatchers.jsonPath(".countryname").value("Germany"))
       .andExpect(MockMvcResultMatchers.jsonPath(".countrycapital").value("Berlin"))
           .andDo(print());

      
	   
	   
		
		
	}
    
    @Test
   	@Order(3)
   	public void test_getcountrybyname() throws Exception {
   	Country country= new Country(3,"Germany","Berlin");
   	String countryname="Germany";
   	   
   	   when(countryService.getcountrybyname(countryname)).thenReturn(country);
   	   this.mockMvc.perform(get("/getcountries/countryname").param("name", countryname))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath(".id").value(3))
          .andExpect(MockMvcResultMatchers.jsonPath(".countryname").value("Germany"))
          .andExpect(MockMvcResultMatchers.jsonPath(".countrycapital").value("Berlin"))
              .andDo(print());

         }
    
    @Test
   	@Order(4)
   	public void test_addcountry() throws Exception {
   	Country country= new Country(3,"Germany","Berlin");
  
   	   
   	   when(countryService.addCountry(country)).thenReturn(country);
   	   
   	   ObjectMapper mapper=new ObjectMapper();
   	   String jsonbody= mapper.writeValueAsString(country);
   	   
   	 this.mockMvc.perform(post("/addcountry")
   			 .content(jsonbody)
   			 .contentType(MediaType.APPLICATION_JSON)
   			
   			 )
   	.andExpect(status().isOk())
   	.andDo(print());
    

         }
    
    @Test
   	@Order(5)
   	public void test_updatecountry() throws Exception {
   	Country country= new Country(3,"Germany","Berlin");
   	int CountryID=3;
  
   	   
   	   when(countryService.getcountrybyID(CountryID)).thenReturn(country);
   	   when(countryService.updateCountry(country)).thenReturn(country);
   	   ObjectMapper mapper=new ObjectMapper();
   	   String jsonbody= mapper.writeValueAsString(country);
   	   
   	 this.mockMvc.perform(put("/updatecountry/{id}",CountryID)
   			 .content(jsonbody)
   			 .contentType(MediaType.APPLICATION_JSON)
   			
   			 )
   	.andExpect(status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath(".countryname").value("Germany"))
    .andExpect(MockMvcResultMatchers.jsonPath(".countrycapital").value("Berlin"))
   	.andDo(print());
    

         }
    
    @Test
   	@Order(6)
   	public void test_deletecountry() throws Exception {
   	Country country= new Country(3,"Germany","Berlin");
   	int CountryID=3;
  
   	   
   	   when(countryService.getcountrybyID(CountryID)).thenReturn(country);
   	   
   	 this.mockMvc.perform(delete("/deletecountry/{id}",CountryID)
  
   			
   			 )
   	.andExpect(status().isOk());
   

         }
}

