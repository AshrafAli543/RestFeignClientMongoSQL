package com.analysis.knowledge.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analysis.knowledge.entitySQL.Employee;
import com.analysis.knowledge.feignclients.MongoFeignClient;
import com.analysis.knowledge.feignclients.SqlFeignClient;
import com.analysis.knowledge.model.EmployeeMongoTO;
import com.analysis.knowledge.model.EmployeeTO;
import com.analysis.knowledge.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	SqlFeignClient sqlFeignClient;
	
	@Autowired
	MongoFeignClient mongoFeignClient;
	
	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public String createSQLEmployee(EmployeeTO employeeTo) {
		String methodName = "createSQLEmployee";
		String status = null;
		logger.info("In createSQLEmployee ServiceImpl method ",methodName);
		try {
			Employee employeeToCreate = Employee.builder()
					.id(employeeTo.getId())
					.name(employeeTo.getName())
					.department(employeeTo.getDepartment())
					.salary(employeeTo.getSalary())
					.employmentStatus(employeeTo.getEmploymentStatus())
					.build();
			Employee savedEmployee = sqlFeignClient.saveEmployee(employeeToCreate);
			if(savedEmployee!=null && savedEmployee.getId() == employeeToCreate.getId() && savedEmployee.getName().equals(employeeToCreate.getName())) {
				status = "Employee with Id : "+savedEmployee.getId()+" And Name: "+savedEmployee.getName()+" Saved Successfully";
			}
			else
				status = "Miss Match On Employee Save Details, Recheck";
			
		} catch (Exception e) {
			logger.error("Exception in createSQLEmployee method :", e);
		}
		return status;
	}

	@Override
	public List<Employee> getAllSQLEmployees() {
		List<Employee> responseList = new ArrayList<Employee>();
		logger.info("In getOneSQLEmployee ServiceImpl method ","getOneSQLEmployee");
		try {
			logger.info("Calling sqlFeignClient to get List of Employees ","getOneSQLEmployee");
			responseList = sqlFeignClient.getAllEmployees();
		} catch (Exception e) {
			logger.error("Exception in getOneSQLEmployee method", e);
		}
		return responseList;
	}

	@Override
	public Employee getOneSQLEmployee(int id) {
		Employee employee = null;
		logger.info("In getOneSQLEmployee ServiceImpl method ","getOneSQLEmployee");
		try {
			logger.info("Calling sqlFeignClient to get List of Employees");
			employee = sqlFeignClient.getEmployeeById(id);
		} catch (Exception e) {
			logger.error("Exception in getOneSQLEmployee method", e);
		} 
		return employee;
	}

	@Override
	public String updateSQLEmployee(EmployeeTO employeeTo) {
		String methodName = "updateSQLEmployee";
		String status = null;
		logger.info("In updateSQLEmployee ServiceImpl method ",methodName);
		try {
			Employee employeeToCreate = Employee.builder()
					.id(employeeTo.getId())
					.name(employeeTo.getName())
					.department(employeeTo.getDepartment())
					.salary(employeeTo.getSalary())
					.employmentStatus(employeeTo.getEmploymentStatus())
					.build();
			Employee updatedEmployee = sqlFeignClient.updateEmployee(employeeToCreate);
			if(updatedEmployee!=null && updatedEmployee.getId() == employeeToCreate.getId() && updatedEmployee.getName().equals(employeeToCreate.getName())) {
				status = "Employee with Id : "+updatedEmployee.getId()+" And Name: "+updatedEmployee.getName()+" Updated Successfully";
			}
			else
				status = "Miss Match On Employee Update Details, Recheck";
			
		} catch (Exception e) {
			logger.error("Exception in createSQLEmployee method :", e);
		}
		return status;
	}

	@Override
	public String deleteSQLEmployeeById(int id) {
		String status = null;
		String methodName="deleteSQLEmployeeById";
		logger.info("In deleteSQLEmployeeById ServiceImpl method ",methodName);
		try {
			logger.info("Calling FeignClient to find Employee by Id in ServiceImpl method ",methodName);
			 Employee findEmployee = sqlFeignClient.getEmployeeById(id);
			 logger.info("Calling FeignClient to delete Employee by Id in ServiceImpl method ",methodName);
		        if (findEmployee != null) {
		        	String deleteStatus = sqlFeignClient.deleteEmployee(id);
		        	status="Employee Deleted Successfully :"+deleteStatus;
		        	return status;
		        } else {
		        	status="Employee not found with id: "+id;
		        	return status;
		        }
		} catch (Exception e) {
			logger.info("In Exception",e);
			throw e;
		}
	}

	@Override
	public String createMongoEmployee(EmployeeTO employeeTo) {
		String methodName = "createMongoEmployee";
		String status = null;
		logger.info("In createMongoEmployee ServiceImpl method ",methodName);
		try {
			String savedEmployeeStatus = mongoFeignClient.createEmployee(employeeTo);
			if(savedEmployeeStatus!=null) {
				status = "Employee Saved Successfully: "+savedEmployeeStatus;
			}
			else
				status = "Not able to Create Employee";
			
		} catch (Exception e) {
			logger.error("Exception in createSQLEmployee method :", e);
		}
		return status;
	}

	@Override
	public List<com.analysis.knowledge.entityMongo.Employee> getAllMongoEmployees() {
		String methodName = "getAllMongoEmployees";
		List<com.analysis.knowledge.entityMongo.Employee> employeesList = null;
		
		logger.info("In getAllMongoEmployees ServiceImpl method ",methodName);
		try {
			logger.info("Calling MongoFeignClient to get List of Employees in ServiceImpl method ",methodName);
			employeesList = mongoFeignClient.getAllEmployees();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return employeesList;
	}

	@Override
	public Optional<com.analysis.knowledge.entityMongo.Employee> getOneMongoEmployee(String id) {
		Optional<com.analysis.knowledge.entityMongo.Employee> employee = null;
		logger.info("In getOneSQLEmployee ServiceImpl method ","getOneMongoEmployee");
		try {
			logger.info("Calling sqlFeignClient to get List of Employees");
			employee = mongoFeignClient.getOneEmployee(id);
			if(employee.isPresent())
				logger.info("Fetched Employee successfully ","getOneMongoEmployee");
			else {
				logger.info("Employee not found with provided Id ","getOneMongoEmployee");
				return employee;
			}
		} catch (Exception e) {
			logger.error("Exception in getOneSQLEmployee method", e);
		} 
		logger.info("Successfully Fetched Employee Sending back to Controller","getOneMongoEmployee");
		return employee;
	}

	@Override
	public String updateMongoEmployee(EmployeeMongoTO employeeTo) {
		String methodName = "updateMongoEmployee";
		String status = null;
		logger.info("In updateMongoEmployee ServiceImpl method ",methodName);
		try {
			String updatedEmployee = mongoFeignClient.updateEmployee(employeeTo);
			if(updatedEmployee!=null) {
				status = "Employee with Id : "+employeeTo.getId()+" And Name: "+employeeTo.getName()+" Updated Successfully";
			}
			else
				status = "Miss Match On Employee Update Details, Recheck";
			
		} catch (Exception e) {
			logger.error("Exception in updateMongoEmployee method :", e);
		}
		return status;
	}

	@Override
	public String deleteMongoEmployee(String id) {
		String methodName = "deleteMongoEmployee";
		String status = null;
		logger.info("In deleteMongoEmployee ServiceImpl method ",methodName);
		try {
			String deleteStatus = mongoFeignClient.deleteEmployee(id);
			if(deleteStatus!=null) {
				status = "Employee with Id : "+id+" Updated Successfully";
			}
			else
				status = "Unable to felete employee, Recheck";
			
		} catch (Exception e) {
			logger.error("Exception in deleteMongoEmployee method :", e);
		}
		return status;
	}

}
