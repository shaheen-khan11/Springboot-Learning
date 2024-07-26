package com.dummy.demo.mapper;

import com.dummy.demo.Dto.Employeedto;
import com.dummy.demo.entity.Employee;

public class Employeemapper {

	public static Employeedto maptoemployeedto (Employee emp)
	{
		return new Employeedto(
				emp.getId(),
				emp.getFname(),
				emp.getLname(),
				emp.getEmail()
				);
		
				
	}
	
	public static Employee maptoemployee (Employeedto emp)
	{
		return new Employee(
				emp.getId(),
				emp.getFname(),
				emp.getLname(),
				emp.getEmail()
				);
		
				
	}
}
