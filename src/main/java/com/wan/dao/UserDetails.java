package com.wan.dao;

import java.io.Serializable;

public class UserDetails implements Serializable
{
	private String name;
	private String department;
	
	public UserDetails()
	{
		super();
	}
	
	public UserDetails(String name,String department)
	{
		super();
		this.name=name;
		this.department=department;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String toString()
	{
		return "UserDetails [name="+name+", department="+department+"]";
		
	}
}
