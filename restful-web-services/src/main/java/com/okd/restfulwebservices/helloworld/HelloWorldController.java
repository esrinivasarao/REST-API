package com.okd.restfulwebservices.helloworld;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class HelloWorldController {
	
//GET
//URI
//Method returning string
	
//@RequestMapping(method=RequestMethod.GET,path="hello-world")
@GetMapping(path="/hello-world")
public String helloWorld() {
	return "Hello World";
}
	
	
//Return Object or Bean
@GetMapping(path="/hello-world-bean")
public HelloWorldBean helloWorldBean() {
	return new HelloWorldBean("Hello World Bean Example");
}

//Return Object or Bean
//Path Variable /hello-world/path-variable/{name}
@GetMapping(path="/hello-world/path-variable/{name}")
public HelloWorldBean helloWorldBean(@PathVariable String name) {
	return new HelloWorldBean(String.format("Hello World, %s",name));
	//throw new  RuntimeException(" Something went wrong "); // for error handling in React JS 
	
}


}
