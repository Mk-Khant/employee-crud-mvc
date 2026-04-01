package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeDao employeeDao;
	private final DepartmentDao departmentDao;
	
	public void deleteEmployeeById(long id) {
		employeeDao.deleteById(id);
	}
	
	//findbyid
	public Employee findEmployeeById(long id) {
		return employeeDao.findById(id).get();
		
	}
	
	public List<Employee> listEmployeeByDepartmentId(long id){
		return employeeDao.listEmployeeByDepartmentId(id);
	}
	
	//findAll
	public List<Department> listAllDepartments(){
		return departmentDao.findAll();
	}
	
	public List<Employee> listAllEmployees(){
		return employeeDao.findAll();
	}
 	
	//create
	public void createDepartment(Department department) {
		departmentDao.save(department);
	}
	
	//create fk owning table
	@Transactional
	public void createEmployee(Employee employee,long departmentId) {
		Department department = departmentDao.findById(departmentId).get();
		department.addEmp(employee);
		employeeDao.save(employee);
	}
}
