package com.analysis.knowledge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMongoTO {

	@JsonProperty(value = "id")
	@NotNull(message = "Id Cannot be Null")
	private String id;

	@JsonProperty(value = "name")
	@NotNull(message = "Name Cannot be Null")
	private String name;

	@JsonProperty(value = "department")
	@NotNull(message = "Department Cannot be Null")
	private String department;

	@JsonProperty(value = "salary")
	@NotNull(message = "Department Cannot be Null")
	private Double salary;

	@JsonProperty(value = "employment_status")
	@NotNull(message = "Employement Status Cannot be Null")
	private String employmentStatus;
}
