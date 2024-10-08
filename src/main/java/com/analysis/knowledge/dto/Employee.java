package com.analysis.knowledge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
