package com.kh.asdf.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.asdf.model.dto.SensorDto;
import com.kh.asdf.model.entity.Sensor;
import com.kh.asdf.model.service.SensorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/sensor")
@RequiredArgsConstructor
public class SensorController {
	private final SensorService sensorService;
	@PostMapping
	public ResponseEntity<?> receive(@RequestBody SensorDto dto){
		log.info("데이터 수신 : {}",dto);
		sensorService.save(dto);
		return ResponseEntity.ok("ㅃ2");
	}
	
	@GetMapping
	public ResponseEntity<List<Sensor>> findAll() {
		return ResponseEntity.ok(sensorService.findAll());
	}

}
