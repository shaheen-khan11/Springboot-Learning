package com.dummy.demo.service;

import java.util.List;

import com.dummy.demo.Dto.Employeedto;

public interface Employeeservice {
	
	Employeedto createemployee(Employeedto employeedto);
	
	Employeedto getemployeebyid(long employeeid);

	List<Employeedto> getallEmployees();
	
	Employeedto updateEmployee(Long eid, Employeedto uemp);
	
	void deleteEmployee(Long eid);
}
