package com.huntercodexs.demo.controller;

import com.huntercodexs.demo.annotation.logger.Help4DevsLoggerAnnotation;
import com.huntercodexs.demo.dto.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class Help4DevsTestController {

	/**
	 * GET http://localhost:35000/api/logger
	 * */
	@Help4DevsLoggerAnnotation
	@GetMapping(path = "/api/logger")
	public ResponseEntity<Void>logger() {
		System.out.println("logger is running");
		return ResponseEntity.ok().build();
	}

	/**
	 * POST http://localhost:35000/api/validation {"id": 1, "age": 40,"name": "12"}
	 * */
	@ResponseBody
	@PostMapping(path = "/api/validation")
	public ResponseEntity<Void>validation(@Valid @RequestBody PersonDto personDto) {
		System.out.println("test is running");
		return ResponseEntity.ok().build();
	}

}
