package com.kh.asdf.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SensorDto {
	private Double voltage;
	private Double current;
	private Double power;
	private Double energy;
	private Double frequency;
	private Double powerFactor;
	private String timestamp;
}
