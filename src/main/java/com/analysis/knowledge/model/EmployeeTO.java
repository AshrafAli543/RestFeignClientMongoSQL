package com.analysis.knowledge.model;



import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTO {

	
	@Id
	@JsonProperty(value = "id")
	@NotNull(message = "Id cannot be null")
    private int id;

	@JsonProperty(value = "name")
	@NotNull(message = "Name cannot be null")
	private String name;

	@JsonProperty(value = "department")
	@NotNull(message = "Department cannot be null")
	private String department;

	@JsonProperty(value = "salary")
	@NotNull(message = "Salary cannot be null")
	private Double salary;
	  
	@JsonProperty(value = "employment_status")
	@NotNull(message = "Employment Status cannot be null")
	private String employmentStatus;
	
	
}
