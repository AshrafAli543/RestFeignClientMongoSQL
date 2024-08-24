package com.analysis.knowledge.rest;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analysis.knowledge.entitySQL.Employee;
import com.analysis.knowledge.model.EmployeeMongoTO;
import com.analysis.knowledge.model.EmployeeTO;
import com.analysis.knowledge.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mongo-sql")
public class MongoSqlController {

	private static final Logger logger = LogManager.getLogger(MongoSqlController.class);

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/basepath")
	public String getBaseMethod() {
		return "In Base Method - MongoSqlController";
	}

	@PostMapping("/employee-sql-create")
	public String createSQLEmployee(@Valid @RequestBody EmployeeTO employeeTo) {
		logger.info("In createSQLEmployee controller method", "createSQLEmployee");
		return employeeService.createSQLEmployee(employeeTo);
	}

	@GetMapping("/employee-sql-read")
	public List<Employee> getAllSQLEmployees() {
		logger.info("In getAllSQLEmployees controller method", "getAllSQLEmployees");
		return employeeService.getAllSQLEmployees();
	}

	@GetMapping("/employee-sql-read-one")
	public Employee getOneSQLEmployee(@RequestParam int id) {
		logger.info("In getOneSQLEmployee controller method ", "getOneSQLEmployee");
		return employeeService.getOneSQLEmployee(id);
	}

	@PutMapping("/employee-sql-update")
	public String updateSQLEmployee(@Valid @RequestBody EmployeeTO employeeTo) {
		logger.info("In updateSQLEmployee controller method ", "updateSQLEmployee");
		return employeeService.updateSQLEmployee(employeeTo);
	}

	@DeleteMapping("/employee-sql-delete")
	public String deleteSQLEmployeeById(@RequestParam int id) {
		logger.info("In deleteSQLEmployeeById controller method ", "deleteSQLEmployeeById");
		return employeeService.deleteSQLEmployeeById(id);
	}

	// MONGO CALLS

	@PostMapping("/employee-mongo-create")
	public String createMongoEmployee(@RequestBody EmployeeTO employeeTo) {
		logger.info("In createMongoEmployee controller method ", "createMongoEmployee");
		return employeeService.createMongoEmployee(employeeTo);
	}

	@GetMapping("/employee-mongo-read")
	public List<com.analysis.knowledge.entityMongo.Employee> getAllMongoEmployees() {
		logger.info("In getAllMongoEmployees controller method ", "getAllMongoEmployees");
		return employeeService.getAllMongoEmployees();
	}

	@GetMapping("/employee-mongo-read-one")
	public ResponseEntity<com.analysis.knowledge.entityMongo.Employee> getOneMongoEmployee(@RequestParam String id) {
		logger.info("In getOneMongoEmployee controller method ", "getOneMongoEmployee");
		Optional<com.analysis.knowledge.entityMongo.Employee> employee = employeeService.getOneMongoEmployee(id);
		com.analysis.knowledge.entityMongo.Employee employeeFound = new com.analysis.knowledge.entityMongo.Employee();
		if(employee.isPresent()) {
			employeeFound = employee.get();
			return ResponseEntity.ok(employeeFound);
		}
		else {
				return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/employee-mongo-update")
	public String updateMongoEmployee(@RequestBody EmployeeMongoTO employeeTo) {
		logger.info("In updateMongoEmployee controller method ", "updateMongoEmployee");
		return employeeService.updateMongoEmployee(employeeTo);
	}

	@DeleteMapping("/employee-mongo-delete")
	public String deleteMongoEmployee(@RequestParam String id) {
		logger.info("In updateMongoEmployee controller method ", "updateMongoEmployee");
		return employeeService.deleteMongoEmployee(id);
	}

}
