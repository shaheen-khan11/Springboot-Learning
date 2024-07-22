package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	 @GetMapping
	    public String getUsers(@RequestParam(value = "page", defaultValue="1") int page
	    		, @RequestParam(value = "limit", defaultValue="10") int limit) // Query parameter to filter data 
	 {
	        if (page <= 0)
	        	
	        { 
	        	return "Invalid page number!! Page number must be greater than zero";
	        }
	        if (limit <= 0)
	        {
	        	return "Limit must be greater than zero";
	        }
	        
	        return "HTPP GET request for page:" + page + "with the limit upto" + limit;
	    }
	           
	
		
	
	
	@GetMapping(path="/{UserID}") //Path Parameter
	public String GetUser(@PathVariable String UserID)
	{
		return "HTTP GET request was sent for specific user:"+ UserID ;
	
		
	}
	
	@PostMapping
	public String PostUsers()
	{
		return "HTTP POST request was sent";
	
		
	}
	
	@PutMapping
	public String PutUsers()
	{
		return "HTTP PUT request was sent";
	
		
	}
	
	@DeleteMapping
	public String DeleteUsers()
	{
		return "HTTP DELETE request was sent";
	
		
	}

}
