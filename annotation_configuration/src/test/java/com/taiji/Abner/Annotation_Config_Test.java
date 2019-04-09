package com.taiji.Abner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.taiji.Abner.bean.BeanBoth;


@RunWith(SpringRunner.class)
@ComponentScan(basePackages="com.taiji.Abner.bean")
@ContextConfiguration(classes = { Annotation_Config_Test.class })
public class Annotation_Config_Test {
	
	@Autowired
	private BeanBoth beanBoth;
	
	@Test
	public void test() {
		System.out.println("测试开始");
		System.out.println(beanBoth.getBeanOne().getName());
		System.out.println(beanBoth.getBeanTwo().getName());

		
	}

}
