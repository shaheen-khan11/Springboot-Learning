package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Country;
import com.example.demo.services.CountryService;

@RestController
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/getcountries")
	public List<Country> getCountries(){
//		return countryService.getAllCountries();
	
		
	   return	countryService.getAllCountries();
		
		
		
	}

	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountrybyId(@PathVariable (value="id") int id){
		
//		return countryService.getcountrybyID(id);
//		
		try{
			Country country= countryService.getcountrybyID(id);
			return new ResponseEntity <Country>(country,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountrybyName(@RequestParam (value="name") String countryname){
		
//		return countryService.getcountrybyname(countryname);
		
		try{
			Country country= countryService.getcountrybyname(countryname);
			return new ResponseEntity <Country>(country,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping("/addcountry")
	public Country addCountry( @RequestBody Country country) {
		
       return countryService.addCountry(country);
		
		
		
		
	}
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id,@RequestBody Country country) {
		
//		return countryService.updateCountry(country);
//	
		try {
			
			Country exist=countryService.getcountrybyID(id);
			exist.setCountryname(country.getCountryname());
			exist.setCountrycapital(country.getCountrycapital());
			
			Country update=countryService.updateCountry(exist);
			return new ResponseEntity<Country> (update,HttpStatus.OK);
			
		}
		
		catch(Exception e)
		{
			return new ResponseEntity <>(HttpStatus.CONFLICT);
		}
		
	}
	
    @DeleteMapping("/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable (value="id") int id) {
		
//		 return countryService.deleteCountry(id);
try {
			
			Country exist=countryService.getcountrybyID(id);
			countryService.deleteCountry(id);
			
			return new ResponseEntity<Country> (exist,HttpStatus.OK);
			
		}
		
		catch(Exception e)
		{
			return new ResponseEntity <>(HttpStatus.CONFLICT);
		}
    	
		
	}
	
	

	

}
