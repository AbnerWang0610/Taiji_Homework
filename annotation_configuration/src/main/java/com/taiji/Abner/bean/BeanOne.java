package com.taiji.Abner.bean;

import org.springframework.stereotype.Component;

@Component("beanOne")
public class BeanOne {
	
	String name="Wahaha";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
