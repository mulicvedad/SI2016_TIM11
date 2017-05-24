package com.timxyz.controllers;

import com.timxyz.models.Role;
import com.timxyz.services.RoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController extends ReadOnlyController<Role, RoleService>  {
}
