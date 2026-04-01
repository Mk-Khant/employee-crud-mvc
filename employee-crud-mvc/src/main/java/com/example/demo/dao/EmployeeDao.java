package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long>{
	@Query("""
			select e from Employee e where e.department.id =:id
		""")
List<Employee> listEmployeeByDepartmentId(@Param("id")long departmentId);
}
