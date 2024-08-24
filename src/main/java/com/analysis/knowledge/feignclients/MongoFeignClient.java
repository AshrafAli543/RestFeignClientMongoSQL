package com.analysis.knowledge.feignclients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.analysis.knowledge.entityMongo.Employee;
import com.analysis.knowledge.model.EmployeeMongoTO;
import com.analysis.knowledge.model.EmployeeTO;


@FeignClient(name = "${feign.mongo.name}", url = "${feign.mongo.url}")
public interface MongoFeignClient {
	
	@PostMapping("/employee-create")
	public String createEmployee(@RequestBody EmployeeTO employeeTO);
	
	@GetMapping("/employees-read")
	public List<Employee> getAllEmployees();
	
	@GetMapping("/employees-read-one")
	public Optional<Employee> getOneEmployee(@RequestParam String id);
	
	@PutMapping("/employee-update")
	public String updateEmployee(@RequestBody EmployeeMongoTO employeeTO);
	
	@DeleteMapping("/employee-delete")
	public String deleteEmployee(@RequestParam String id);
	

}
