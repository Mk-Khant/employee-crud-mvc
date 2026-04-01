package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@GetMapping("/")
	public String home() {
		return "redirect:/list-employees";
	}
	
	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam("id")long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/list-employees";
	}
	
	@GetMapping("/update-employee")
	public String updateEmployee(@RequestParam("id")Long id,Model model) {
		model.addAttribute("id", id);
		model.addAttribute("employee", employeeService.findEmployeeById(id));
		model.addAttribute("departments", employeeService.listAllDepartments());
		return "employee";
	}
	
	@GetMapping("/list-employees-by-department")
	public String listEmployeesByDepartmentId(@RequestParam("id")long id,Model model) {
		model.addAttribute("employees", employeeService.listEmployeeByDepartmentId(id));
		return "employees";
	}

	@GetMapping("/list-departments")
	public String listDepartment(Model model) {
		model.addAttribute("departments", employeeService.listAllDepartments());
		return "departments";
	}
	
	@GetMapping("/create-department")
	public String createDepartment(Model model) {
		model.addAttribute("department", new Department());
		return "department";
	}
	
	@PostMapping("/create-department")
	public String saveDepartment(Department department,BindingResult result) {
		if(result.hasErrors()) {
			return "department";
		}
		employeeService.createDepartment(department);
		return "redirect:/list-departments";
	}
	
	@GetMapping("/list-employees")
	public String listEmployee(Model model) {
		model.addAttribute("employees", employeeService.listAllEmployees());
		return "employees";
	}
	
	@GetMapping("/create-employee")
	public String createEmployee(Model model) {
		model.addAttribute("employee",new Employee());
		model.addAttribute("id",null);
		model.addAttribute("departments", employeeService.listAllDepartments());
		return "employee";
	}
	
	@PostMapping("/create-employee")
	public String saveEmployee(Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			return "employee";
		}
		employeeService.createEmployee(employee, employee.getDepartment().getId());
		return "redirect:/list-employees";
	}
	
}
