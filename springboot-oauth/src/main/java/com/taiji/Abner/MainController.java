package com.taiji.Abner;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/hello")
	public String main() {
		return "hello";
	}
	
	@GetMapping("/ok")
	public String ok() {
		return "okokok";
	}

}
