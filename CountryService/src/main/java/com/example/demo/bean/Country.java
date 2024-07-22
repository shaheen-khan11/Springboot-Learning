package com.example.demo.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Country")
public class Country {
	@Id
	@Column(name="id")
	int id;
	@Column(name="countryname")
	String countryname;
	@Column(name="countrycapital")
	String countrycapital;
	
	
	public Country() {
		
	}
	public Country(int id, String  countryname, String countrycapital) {
		this.id=id;
		this.countryname=countryname;
		this.countrycapital=countrycapital;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getCountrycapital() {
		return countrycapital;
	}
	public void setCountrycapital(String countrycapital) {
		this.countrycapital = countrycapital;
	}
	
	

}
