package com.taiji.Abner.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hehheheh";
	}

	@GetMapping("/index")
	public String index() {
		return "hello";
	}

	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile mf) throws IllegalStateException, IOException {
		File localFile = new File("D://Download the application/Google Chrome/" + mf.getOriginalFilename());
		mf.transferTo(localFile);
		return "ok";
	}

}
