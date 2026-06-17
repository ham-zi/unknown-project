package com.kh.asdf.model.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.kh.asdf.model.dao.SensorRepository;
import com.kh.asdf.model.dto.SensorDto;
import com.kh.asdf.model.entity.Sensor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SensorService {
	private final SensorRepository sensorRepository;
	public void save(SensorDto dto) {
		Sensor s = new Sensor();
		s.setCurrent(dto.getCurrent());
		s.setPower(dto.getPower());
		s.setVoltage(dto.getVoltage());
		s.setEnergy(dto.getEnergy());
		s.setFrequency(dto.getFrequency());
		s.setTimestamp(dto.getTimestamp());
		s.setPowerFactor(dto.getPowerFactor());
		sensorRepository.save(s);
	}
	public List<Sensor> findAll() {
		return sensorRepository.findAll();
	}



}
