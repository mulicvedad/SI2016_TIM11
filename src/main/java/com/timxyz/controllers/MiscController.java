package com.timxyz.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiscController {
    @ResponseBody
    public ResponseEntity catchAllOptions(String path) {
        return ResponseEntity.ok(true);
    }
}
