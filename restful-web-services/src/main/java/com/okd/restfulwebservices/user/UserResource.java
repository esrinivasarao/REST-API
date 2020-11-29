package com.okd.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
// static important for directly access in the code 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	//GET /users
	//retrieve all users
	@GetMapping("/users")
	public List<User> 	retrieveAllUsers(){
		
		return service.findAll();
	}
		
	
	//GET User /users/{id}
	@GetMapping("/users/{id}")
	//Code Before Handling Runtime Exception 
	/*
	 * public User retrieveUser(@PathVariable int id) { return service.findOne(id);
	 * }
	 */
	
	
 
	/* Before Hateoas 
	 * public User retrieveUser(@PathVariable int id) {
	 * 
	 * User user = service.findOne(id); if ( user==null ) throw new
	 * UserNotFoundException("id="+id) ;
	 * 
	 * return user ; }
	 */
	
	
	//Code After Handling Runtime Exception
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		
		User user = service.findOne(id);
		if ( user==null ) throw new UserNotFoundException("id="+id) ;
		
		//"all-users" , SERVER_PATH  + "/users"
        // retrieve all users by link sending 
		
         EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
	  // resource.add(linkTo.with
		
		return resource  ; 
	}
	
  //Delete User 
   @DeleteMapping("/users/{id}")
   public void deleteUser(@PathVariable int id) {
		
		User user = service.deleteById(id);
		if ( user==null ) throw new UserNotFoundException("id="+id) ;
		
	}
	
	// Create User
	//POST Mapping
	
	/*
	 * @PostMapping("/users") public void createUser(@RequestBody User user) { User
	 * savedUser = service.save(user); }
	 */
	
	//Send URI back with ID created
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(savedUser.getId()).toUri() ;
		return ResponseEntity.created(location).build();
	}
	
	
}
