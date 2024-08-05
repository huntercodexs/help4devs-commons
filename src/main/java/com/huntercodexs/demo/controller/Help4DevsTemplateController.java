package com.huntercodexs.demo.controller;

import com.huntercodexs.demo.dto.UserDto;
import com.huntercodexs.demo.handler.error.Help4DevsSampleError;
import com.huntercodexs.demo.handler.error.Help4DevsServiceError;
import com.huntercodexs.demo.handler.exception.Help4DevsSampleException;
import com.huntercodexs.demo.handler.exception.Help4DevsServiceException;
import com.huntercodexs.demo.services.basic.Help4DevsBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.huntercodexs.demo.services.data.Help4DevsDataRandomService.randomGuid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class Help4DevsTemplateController {

	@Autowired
	Help4DevsBaseService help4DevsBaseService;

	@GetMapping(path = "/api/get/{id}")
	@ResponseBody
	public ResponseEntity<?> get(
			@Valid @PathVariable int id,
			@Valid @RequestBody Object object,
			@Valid @RequestHeader (name="Authorization") String header,
			final HttpServletRequest httpServletRequest
	) {
		//return ResponseEntity.ok().body("GET OK - " + id);
		return new ResponseEntity<>("GET OK - " + id, HttpStatus.OK);
	}

	@PostMapping(path = "/api/post/{id}")
	@ResponseBody
	public ResponseEntity<?> post(
			@Valid @PathVariable int id,
			@Valid @RequestBody Object object,
			@Valid @RequestHeader (name="Authorization") String header,
			final HttpServletRequest httpServletRequest
	) {
		//return ResponseEntity.created().body("POST OK - " + id);
		return new ResponseEntity<>("POST OK - " + id, HttpStatus.CREATED);
	}

	@PutMapping(path = "/api/put/{id}")
	@ResponseBody
	public ResponseEntity<?> put(
			@Valid @PathVariable int id,
			@Valid @RequestBody Object object,
			@Valid @RequestHeader (name="Authorization") String header,
			final HttpServletRequest httpServletRequest
	) {
		//return ResponseEntity.accepted().body("PUT OK - " + id);
		return new ResponseEntity<>("PUT OK - " + id, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/api/delete/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(
			@Valid @PathVariable int id,
			@Valid @RequestBody Object object,
			@Valid @RequestHeader (name="Authorization") String header,
			final HttpServletRequest httpServletRequest
	) {
		//return ResponseEntity.accepted().body("DELETE OK - " + id);
		return new ResponseEntity<>("DELETE OK - " + id, HttpStatus.ACCEPTED);
	}

	@PatchMapping(path = "/api/patch/{id}")
	@ResponseBody
	public ResponseEntity<?> patch(
			@Valid @PathVariable int id,
			@Valid @RequestBody Object object,
			@Valid @RequestHeader (name="Authorization") String header,
			final HttpServletRequest httpServletRequest
	) {
		//return ResponseEntity.accepted().body("PATCH OK - " + id);
		return new ResponseEntity<>("PATCH OK - " + id, HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/api/handler/exception/{type}")
	@ResponseBody
	public ResponseEntity<?> handlerException(
			@PathVariable String type
	) {
		if (type.equals("service")) {
			throw new Help4DevsServiceException(randomGuid(), Help4DevsServiceError.HELP4DEVS_UNKNOWN_ERROR);
		}
		throw new Help4DevsSampleException(randomGuid(), Help4DevsSampleError.HELP4DEVS_SERVICE_UNKNOWN_ERROR);
	}

	@PostMapping(path = "/api/handler/argument/exception")
	@ResponseBody
	public ResponseEntity<?> handlerArgumentException(
			@Valid @RequestBody UserDto userDto
	) {
		//return ResponseEntity.accepted().body("PATCH OK - " + id);
		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/api/handler/method/exception")
	@ResponseBody
	public ResponseEntity<?> handlerMethodNotAllowedException(
			@RequestBody @Valid UserDto userDto
	) {
		//return ResponseEntity.accepted().body("PATCH OK - " + id);
		return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
	}

}
