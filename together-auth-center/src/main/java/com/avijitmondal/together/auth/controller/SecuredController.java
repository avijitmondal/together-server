package com.avijitmondal.together.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

	@GetMapping
	public String securedResource(Authentication auth) {
		return "This resource is secured. Authentication: " + auth.getName() + "; Authorities: " + auth.getAuthorities();
	}
	
}