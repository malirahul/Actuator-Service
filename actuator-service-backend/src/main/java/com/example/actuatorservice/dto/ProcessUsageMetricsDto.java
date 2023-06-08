package com.example.actuatorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessUsageMetricsDto {
	
	private String name;
	private String description;
	private String measurements;

}
