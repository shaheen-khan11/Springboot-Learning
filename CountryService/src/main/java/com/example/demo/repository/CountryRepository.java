package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Country;

public interface CountryRepository extends JpaRepository <Country, Integer>{
	

}
