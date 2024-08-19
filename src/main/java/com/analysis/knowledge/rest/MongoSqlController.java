package com.analysis.knowledge.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mongo-sql")
public class MongoSqlController {
	
	@GetMapping("/basepath")
	public String getBaseMethod() {
		return "In Base Method - MongoSqlController";
	}
	

}
