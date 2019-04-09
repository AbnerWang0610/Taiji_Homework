package com.taiji.Abner.bean;

import org.springframework.stereotype.Component;

@Component("beanTwo")
public class BeanTwo {
	
	String name ="哇哈哈";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
