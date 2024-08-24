package com.analysis.knowledge.service;

import java.util.List;
import java.util.Optional;

import com.analysis.knowledge.entitySQL.Employee;
import com.analysis.knowledge.model.EmployeeMongoTO;
import com.analysis.knowledge.model.EmployeeTO;

public interface EmployeeService {

	String createSQLEmployee(EmployeeTO employeeTo);

	List<Employee> getAllSQLEmployees();

	Employee getOneSQLEmployee(int id);

	String updateSQLEmployee(EmployeeTO employeeTo);

	String deleteSQLEmployeeById(int id);

	String createMongoEmployee(EmployeeTO employeeTo);

	List<com.analysis.knowledge.entityMongo.Employee> getAllMongoEmployees();

	Optional<com.analysis.knowledge.entityMongo.Employee> getOneMongoEmployee(String id);

	String updateMongoEmployee(EmployeeMongoTO employeeTo);

	String deleteMongoEmployee(String id);

}
