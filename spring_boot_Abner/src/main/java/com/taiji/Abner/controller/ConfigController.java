package com.taiji.Abner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.Abner.MySelf;

@RestController
public class ConfigController {

	@Autowired
	public MySelf myself;

	@RequestMapping("/myself")
	public String myself() {
		return myself.toString();
	}

}
