package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Country;
import com.example.demo.controller.AddResponse;
import com.example.demo.repository.CountryRepository;

@Component
@Service
public class CountryService {
	
//	static HashMap <Integer, Country> countryIdMap;
//	
//	public CountryService()
//	{
//		
//		countryIdMap= new HashMap<Integer,Country>();
//		
//		Country indiaCountry= new Country(1, "India","New Delhi");
//		Country usaCountry= new Country(2, "USA","Washington DC");
//		Country ukCountry= new Country(3, "UK","London");
//		
//		
//		countryIdMap.put(1, indiaCountry);
//		countryIdMap.put(2, usaCountry);
//		countryIdMap.put(3, ukCountry);
//		
//	}
	
	@Autowired
	CountryRepository countryrepository;
	
	public List<Country> getAllCountries()
	{
//		List countries= new ArrayList (countryIdMap.values());
//		return countries;
		return countryrepository.findAll();
		
		
	}
	
	public Country getcountrybyID (int id)
	{
//		Country c=countryIdMap.get(id);
//		return c;
//		
		return countryrepository.findById(id).get();
	}
	
	public Country getcountrybyname (String Name)
	{
//		Country c= null;
//		for (int i:countryIdMap.keySet()) {
//			if (countryIdMap.get(i).getCountryname().equals(Name)) {
//				c=countryIdMap.get(i);
//			}
//			
//		}
//		return c;
//	    
		
		List<Country> countries=countryrepository.findAll();
		
		Country cont = null;
		
		for (Country con:countries) {
			
			if (con.getCountryname().equalsIgnoreCase(Name))
			{
				cont=con;
			}
		}
		 return cont;
		
	}
	
	public Country addCountry(Country country) {
//		
//		country.setId(getMaxId());
//		countryIdMap.put(country.getId(), country);
//		return country;
		
		country.setId(getMaxId());
		countryrepository. save(country);
		return country;
		
		
	}
	
	public  int getMaxId() {
//		int max=0;
//		for (int i: countryIdMap.keySet())
//		{
//			if (max < i){
//				max=i;
//	
//			}
//		}
//		return max+1;
		
	return countryrepository.findAll().size()+1;
	}
	
	public Country updateCountry(Country country) {
//		if (country.getId()>0) {
//			countryIdMap.put(country.getId(), country);
//		}
//		return country;
		countryrepository. save(country);
		return country;
	}
	
	public AddResponse deleteCountry(int id)
	{
//		countryIdMap.remove(id);
//		AddResponse res= new AddResponse();
//		res.setMsg("Country deleted");
//		res.setId(id);
//		return res;
		
		countryrepository.deleteById(id);
		AddResponse res= new AddResponse();
		res.setMsg("Country deleted");
		res.setId(id);
		return res;
	}
		
	}
