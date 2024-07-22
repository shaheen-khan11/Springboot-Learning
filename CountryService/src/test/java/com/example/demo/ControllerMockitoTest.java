package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Country;
import com.example.demo.controller.AddResponse;
import com.example.demo.controller.CountryController;
import com.example.demo.repository.CountryRepository;
import com.example.demo.services.CountryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes= {ControllerMockitoTest.class})
@TestMethodOrder(OrderAnnotation.class)
public class ControllerMockitoTest {
	
	@Mock
	CountryService countryservice;
	
	@InjectMocks
	CountryController countrycontroller;
	
	public List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getallcountries() {
		 List<Country> mycountries=new ArrayList();
		   mycountries.add(new Country(1,"India","Delhi"));
		   mycountries.add(new Country(2,"USA","Washington"));
		   
	   
	   when(countryservice.getAllCountries()).thenReturn(mycountries);
	   assertEquals(2,countrycontroller.getCountries().size());
	   
		
		
	}
	

	@Test
	@Order(2)
	public void test_getcountrybyId() {
	Country country= new Country(3,"Germany","Berlin");
	int countryId=3;
	   
	   when(countryservice.getcountrybyID(countryId)).thenReturn(country);
	   ResponseEntity<Country> response=countrycontroller.getCountrybyId(countryId);
	   assertEquals(HttpStatus.OK,response.getStatusCode());
	   assertEquals(countryId,response.getBody().getId());
	   
	   
		
		
	}

	@Test
	@Order(3)
	public void test_getcountrybyname() {
	Country country= new Country(3,"Germany","Berlin");
	String countryname="Germany";
	   
	   when(countryservice.getcountrybyname(countryname)).thenReturn(country);
	   ResponseEntity<Country> response=countrycontroller.getCountrybyName(countryname);
	   assertEquals(HttpStatus.OK,response.getStatusCode());
	   assertEquals(countryname,response.getBody().getCountryname());
	   
	   
		
		
	}
	
	@Test
	@Order(4)
	public void test_addcountry() {
	Country country= new Country(3,"Germany","Berlin");
	
	   
	   when(countryservice.addCountry(country)).thenReturn(country);
	   Country response=countrycontroller.addCountry(country);
	   
	   assertEquals(country.getCountryname(),response.getCountryname());
	   
	   
		
		
	}
	
	@Test
	@Order(5)
	public void test_updatecountry() {
	Country country= new Country(3,"Germany","Berlin");
	int cid=3;
	
	   
	   when(countryservice.getcountrybyID(cid)).thenReturn(country);
	   when(countryservice.updateCountry(country)).thenReturn(country);
	   ResponseEntity<Country> response=countrycontroller.updateCountry(cid, country);
	   
	   assertEquals(HttpStatus.OK,response.getStatusCode());
	   assertEquals(cid,response.getBody().getId());
	   
		
		
	}
	@Test
	@Order(6)
	public void test_deletecountry() {
	Country country= new Country(3,"Germany","Berlin");
	int cid=3;
	
	   
	   when(countryservice.getcountrybyID(cid)).thenReturn(country);
	   ResponseEntity<Country> response=countrycontroller.deleteCountry(cid);
	   
	   assertEquals(HttpStatus.OK,response.getStatusCode());
	   
		
		
	}
}
