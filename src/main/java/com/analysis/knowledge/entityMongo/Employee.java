package com.analysis.knowledge.entityMongo;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Document(collection="Employee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	
	@Id
	@JsonProperty(value = "id")
    private String id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "department")
	private String department;

	@JsonProperty(value = "salary")
	private Double salary;
	  
	@JsonProperty(value = "employment_status")
	private String employmentStatus;
}
