package com.analysis.knowledge.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.analysis.knowledge.entitySQL.Employee;


@FeignClient(name = "${feign.sql.name}", url = "${feign.sql.url}")
public interface SqlFeignClient {

	@GetMapping(value = "/allEmployees")
	public List<Employee> getAllEmployees();

	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable int id);
	
	@PostMapping("/employee-save")
	public Employee saveEmployee(@RequestBody Employee employee);
	
	@PutMapping("/employee-update")
	public Employee updateEmployee(@RequestBody Employee employee);

	@DeleteMapping("/employee-delete/{id}")
	public String deleteEmployee(@PathVariable int id);
}
