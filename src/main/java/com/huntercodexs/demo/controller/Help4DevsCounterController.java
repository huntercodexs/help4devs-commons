package com.huntercodexs.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class Help4DevsCounterController {

    @GetMapping("/api/counter/{counter}")
    public ResponseEntity<String> counter(@PathVariable(name = "counter") String counter) {

        if (counter.equals("1")) {
            return ResponseEntity.ok().body("One...");
        } else if (counter.equals("2")) {
            return ResponseEntity.ok().body("Two...");
        } else if (counter.equals("3")) {
            return ResponseEntity.ok().body("Three...");
        } else {
            return ResponseEntity.ok().body("Go !");
        }

    }

}
