package controller;



import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="hello")
@SessionAttributes("username")
public class HelloController {
	
	@Autowired
	ApplicationContext app;
	
	@Autowired
	WebApplicationContext wApp;
	
	@GetMapping("/index")
	public String index(Model model){
	   for(String name:app.getBeanDefinitionNames()){
		   System.out.println(app.getBean(name).getClass().getName());
	   }	
	   System.out.println("================");
	   for(String name:wApp.getBeanDefinitionNames()){
		   System.out.println(wApp.getBean(name).getClass().getName());
	   }
	   model.addAttribute("name", "哇哈哈");
		return "hello";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile mf) throws IllegalStateException, IOException {
		File localFile = new File("D://Download the application/Google Chrome/"+mf.getOriginalFilename());
		mf.transferTo(localFile);
		return "ok";		
	}
	
	@GetMapping("/login")
	public String login(String ids[]) throws InterruptedException{
		for(String id:ids){
			System.out.println(id);
		}
		return "login";
	}
	
	@PostMapping("/join")
	public String join(String username,Model model){
		model.addAttribute("username", username);
		return "success";
	}
	
	@GetMapping("/findOne")
	public String findOne(@ModelAttribute("JSESSIONID") String cookieValue, Model model,HttpServletRequest request){
		System.out.println(cookieValue);
		return "hello";
	}
	
	@PostMapping("/add")
	public String add(User user,Model model){
		model.addAttribute("username", user.getName());
		System.out.println(user.getName());
		return "hello";
	}

}
