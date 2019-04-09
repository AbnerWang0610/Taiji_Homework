package com.taiji.Abner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/hello")
	public String show() {
		return "hello";
	}
	
	@GetMapping("/hi")
	public String showHi() {
		return "hi";
	}
	
}
