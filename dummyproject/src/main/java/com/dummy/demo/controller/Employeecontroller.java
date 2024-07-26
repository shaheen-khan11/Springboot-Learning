package com.dummy.demo.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.demo.Dto.Employeedto;
import com.dummy.demo.service.Employeeservice;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class Employeecontroller {
	
	@Autowired
	private Employeeservice employeeservice;
	
	@PostMapping
	public ResponseEntity<Employeedto> createEmployee (@RequestBody Employeedto employeedto){
		Employeedto savedemp=employeeservice.createemployee(employeedto);
		return new ResponseEntity<>(savedemp,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	
	public ResponseEntity<Employeedto> getemployeebyid(@PathVariable ("id") Long id){
		Employeedto empdto=employeeservice.getemployeebyid(id);
		return ResponseEntity.ok(empdto);
	}

	@GetMapping("/all")

	public ResponseEntity<List<Employeedto>> getallEmployees() {
		List<Employeedto> e=employeeservice.getallEmployees();
		return ResponseEntity.ok(e);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employeedto> updateEmployee(@PathVariable ("id") Long employeeid, @RequestBody Employeedto uemp) {
		Employeedto e=employeeservice.updateEmployee(employeeid, uemp);
		return ResponseEntity.ok(e);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable ("id") Long employeeid) {
		employeeservice.deleteEmployee(employeeid);
		return ResponseEntity.ok("Employee deleted!");
	}

}
