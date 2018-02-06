package com.wan.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class UserController
{
    
	public List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
	
	public Gson gson=new Gson();
    
    public UserController()
    {
    	System.out.println("------construcor----");
        UserDetails user1=new UserDetails("User1", "Mechanical");
    	UserDetails user2=new UserDetails("User2", "Electrical");
    	
    	userDetailsList.add(user1);
    	userDetailsList.add(user2);
    }
    
	
    @RequestMapping(value="/userdetails",method=RequestMethod.GET,produces="application/json")
    public String GetUserdetails()
    {
    	System.out.println("------getuserdetails----");
    	
    	
    	
    	
    	for(UserDetails user:userDetailsList)
    	{
    		System.out.println(user);
    	}
    	
    	String userDetailsListJSON= gson.toJson(userDetailsList);
    	
        return userDetailsListJSON;
    }
    
    @RequestMapping(value="/user",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public String ProcessUser(@RequestBody UserDetails userDetails)
    {
    	System.out.println("------adduser----");
    	
    	System.out.println(userDetails);
        boolean nameExist = false;
        String response="";
        
        for(UserDetails ud : userDetailsList)
        {
            if(ud.getName().equals(userDetails.getName()))
            {
                nameExist = true;
                ud.setDepartment(userDetails.getDepartment());
                response="{\"message\":\"failed to add\"}";
                break;
            }
        }
        if(!nameExist)
        {
            userDetailsList.add(userDetails);
            response= gson.toJson(userDetails);
        }
        System.out.println(response);
        
        return response;
    }
    
    @RequestMapping(value="/deleteuser",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity DeleteUser(@RequestBody UserDetails userDetails)
    {
    	System.out.println("------delete----");
        Iterator<UserDetails> it = userDetailsList.iterator();
        while(it.hasNext())
        {
            UserDetails ud = (UserDetails) it.next();
            if(ud.getName().equals(userDetails.getName()))
                it.remove();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}