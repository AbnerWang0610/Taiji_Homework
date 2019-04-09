package com.taiji.Abner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired
	UserService userService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	public static Logger log=LoggerFactory.getLogger(AppTest.class);
	
	@Ignore
	@Test
	public void test() {
		for (int i = 0; i < 9; i++) {
			User user = new User();
			user.setPassword("jjjjjjjjj" + i);
			user.setUsername("haha" + i);
			user.setFlag(1);
			userService.saveUser(user);
		}
	}
	
	@Ignore
	@Test
	public void findTset() {

		System.out.println(userService.findUser());

	}
	
	@Test
	public void findByFlag() {
		System.out.println("请输入flag值：");
		System.out.println(userService.findByFlag(new Scanner(System.in).nextInt()));
	}
	
	@Ignore
	@Test
	public void testPage() {
		String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"username\", \"value\":\"haha1\"}]}}";
		Map searchParameters = new HashMap();
		try {
			searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
			});
		} catch (JsonParseException e) {
			log.error("JsonParseException{}:", e.getMessage());
		} catch (JsonMappingException e) {
			log.error("JsonMappingException{}:", e.getMessage());
		} catch (IOException e) {
			log.error("IOException{}:", e.getMessage());
		}

		Map mapDept = userService.getPage(searchParameters);

		System.out.println(mapDept.get("total"));

		System.out.println(mapDept.get("users"));
	}

}
