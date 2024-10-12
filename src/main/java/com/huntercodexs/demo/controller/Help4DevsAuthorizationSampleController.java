package com.huntercodexs.demo.controller;

import com.huntercodexs.demo.config.OAuthRequestedMatcher;
import com.huntercodexs.demo.services.security.Help4DevsJwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class Help4DevsAuthorizationSampleController {

	@Autowired
	Help4DevsJwtService help4DevsJwtService;

	@Autowired
	OAuthRequestedMatcher oAuthRequestedMatcher;

	@PostMapping(path = "/api/auth/basic")
	@ResponseBody
	public ResponseEntity<?> basicAuth(final HttpServletRequest httpServletRequest) {
		if (oAuthRequestedMatcher.matches(httpServletRequest)) {
			return new ResponseEntity<>(UUID.randomUUID().toString(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping(path = "/api/auth/jwt")
	@ResponseBody
	public ResponseEntity<?> jwtAuth(final HttpServletRequest httpServletRequest) {
		if (oAuthRequestedMatcher.matches(httpServletRequest)) {
			return new ResponseEntity<>(help4DevsJwtService.jwt(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@GetMapping(path = "/api/auth/jwt/check")
	@ResponseBody
	public ResponseEntity<?> jwtAuthCheck(@RequestHeader (name="Authorization") String header) {
		if (help4DevsJwtService.jwtCheck(header)) {
			return new ResponseEntity<>("OK", HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping(path = "/api/auth/jwt-assign")
	@ResponseBody
	public ResponseEntity<?> jwtAssignAuth(final HttpServletRequest httpServletRequest) {
		if (oAuthRequestedMatcher.matches(httpServletRequest)) {
			return new ResponseEntity<>(help4DevsJwtService.jwtAssign(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@GetMapping(path = "/api/auth/jwt-assign/check")
	@ResponseBody
	public ResponseEntity<?> jwtAssignAuthCheck(@RequestHeader (name="Authorization") String header) {
		if (help4DevsJwtService.jwtAssignCheck(header)) {
			return new ResponseEntity<>("OK", HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
