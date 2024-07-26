package com.dummy.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dummy.demo.Dto.Employeedto;
import com.dummy.demo.entity.Employee;
import com.dummy.demo.exception.ResourceNotFound;
import com.dummy.demo.mapper.Employeemapper;
import com.dummy.demo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements Employeeservice {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employeedto createemployee(Employeedto employeeDto) {
        Employee employee = Employeemapper.maptoemployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return Employeemapper.maptoemployeedto(savedEmployee);
    }

    @Override
    public Employeedto getemployeebyid(long employeeId) {
    	Employee employee = employeeRepository.findById(employeeId)
    		    .orElseThrow(() -> new ResourceNotFound("Employee not found with id: " + employeeId));

        return Employeemapper.maptoemployeedto(employee);
    }

	public List<Employeedto> getallEmployees() {
		  List<Employee> employees = employeeRepository.findAll();
	        return employees.stream()
	                .map(Employeemapper::maptoemployeedto)
	                .collect(Collectors.toList());
	}

	@Override
	public Employeedto updateEmployee(Long eid, Employeedto uemp) {
		Employee employee = employeeRepository.findById(eid)
    		    .orElseThrow(() -> new ResourceNotFound("Employee not found with id: " + eid));
        employee.setFname(uemp.getFname());
        employee.setLname(uemp.getLname());
        employee.setEmail(uemp.getEmail());
        Employee updateemployee=employeeRepository.save(employee);
		return (Employeemapper.maptoemployeedto(updateemployee));
	}

	@Override
	public void deleteEmployee(Long eid) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(eid)
    		    .orElseThrow(() -> new ResourceNotFound("Employee not found with id: " + eid));
		
		employeeRepository.deleteById(eid);
		
		
	}
}
