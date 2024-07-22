package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.bean.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.services.CountryService;

@SpringBootTest(classes= {ServiceMockitoTest.class})
@TestMethodOrder(OrderAnnotation.class)
public class ServiceMockitoTest {

	@Mock
	CountryRepository countryrep;
	
	@InjectMocks
	CountryService countryservice;
	
	public List<Country> mycountries;
	@Test
	@Order(1)
	public void test_getallcountries() {
	   List<Country> mycountries=new ArrayList();
	   mycountries.add(new Country(1,"India","Delhi"));
	   mycountries.add(new Country(2,"USA","Washington"));
	   
	   when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(2,countryservice.getAllCountries().size());
	}
	
	@Test @Order(2)
	public void test_getcountrybyId() {
		 Country country = new Country(1, "India", "Delhi");
	        int countryID = 1;

	        when(countryrep.findById(countryID)).thenReturn(Optional.of(country));

	        assertEquals(countryID, countryservice.getcountrybyID(countryID).getId());
	   
	}
	
	@Test
	@Order(3)
	public void test_getcountrybyname() {
	   List<Country> mycountries=new ArrayList();
	   mycountries.add(new Country(1,"India","Delhi"));
	   mycountries.add(new Country(2,"USA","Washington"));
	   
	   String countryName="India";
	   when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals("India",countryservice.getcountrybyname(countryName).getCountryname());
	}
	
	@Test
	@Order(4)
	public void test_addcountry() {
	   Country country= new Country(3,"Germany","Berlin");
	   when(countryrep.save(country)).thenReturn(country);
		assertEquals(country,countryservice.addCountry(country));
	}
	
	@Test
	@Order(5)
	public void test_updatecountry() {
	   Country country= new Country(3,"Germany","Berlin");
	   when(countryrep.save(country)).thenReturn(country);
		assertEquals(country,countryservice.updateCountry(country));
	}
	
	@Test
	@Order(6)
	public void test_deletecountry() {
		  Country country = new Country(3, "Germany", "Berlin");
	        countryservice.deleteCountry(country.getId());
	        verify(countryrep, times(1)).deleteById(country.getId());
	   
	}
}
