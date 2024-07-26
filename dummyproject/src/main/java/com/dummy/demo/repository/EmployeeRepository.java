package com.dummy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dummy.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
