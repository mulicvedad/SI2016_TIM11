package com.timxyz.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAccountController {

	@ResponseBody
	public ResponseEntity get() {
		return ResponseEntity.ok("");
	}
}
