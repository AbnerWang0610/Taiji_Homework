package com.taiji.Abner;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

@Component
public class BankService {
	
	@Secured("ROLE_USER")
	public String show() {
		return "我是一个受保护的Method";
	}

}
