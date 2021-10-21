package com.cognizant.ormlearn;

import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;

@SpringBootApplication
public class OrmLearnApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class); 
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args); 
		employeeService = context.getBean(EmployeeService.class); 
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
		testGetEmployee(); 
		//testGetSkill();
		//testAddEmployee();
		//testUpdateEmployee();
		//testGetDepartment();
		//testAddSkillToEmployee();
	}
	
	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");
	}
	
	private static void testAddEmployee() {
		LOGGER.info("Start");
		Employee employee = null;
		Department department = departmentService.get(1);
		employee.setDepartment(department);
		employeeService.save(employee);
		LOGGER.info("End");
	}
	
	private static void testUpdateEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		Department department = new Department(3, "aa");
		employee.setDepartment(department);
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.info("End");
	}
	
	private static void testGetDepartment() {
		LOGGER.info("Start");
		Department department = departmentService.get(1);
		Set<Employee> employeeList = department.getEmployeeList();
		LOGGER.debug("Department:{}", department);
		LOGGER.debug("Employees:{}",employeeList);
		LOGGER.info("End");
	}
	
	private static void testAddSkillToEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		Skill skill = skillService.get(2);
		employee.getSkillList().add(skill);
		employeeService.save(employee);
		LOGGER.info("End");
	}
	
	private static void testGetSkill() {
		LOGGER.info("Start");
		Skill skill = skillService.get(1);
		LOGGER.debug("Skill:{}", skill);
		LOGGER.info("End");
	}
}
