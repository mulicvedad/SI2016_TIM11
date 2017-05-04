package com.timxyz.controllers;


import com.timxyz.models.Location;
import com.timxyz.services.LocationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController extends BaseController<Location, LocationService> {

}