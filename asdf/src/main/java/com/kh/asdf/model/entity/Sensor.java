package com.kh.asdf.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Sensor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; //PK
	
	private Double voltage; //전압
	@Column(name="currnt_value")
	private Double current; //전력
	private Double power; //
	private Double energy;// 에너지
	private Double frequency; // 주파수
	private Double powerFactor; //역률
	@Column(name="timestamp_value")
	private String timestamp;
}
