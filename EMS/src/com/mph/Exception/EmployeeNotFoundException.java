package com.mph.Exception;

public class EmployeeNotFoundException extends Exception {
	public EmployeeNotFoundException()
	{
		System.out.println("From exception constructor");
	}
	public String toString()
	{
		return "EmployeeNotFoundException raised!!! Please check the credentials";
	}

}
