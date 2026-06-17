package com.kh.asdf.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.asdf.model.entity.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long>{

	
}
