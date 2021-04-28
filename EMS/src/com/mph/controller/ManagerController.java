package com.mph.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mph.model.Employee;
import com.mph.model.Manager;

public class ManagerController extends EmployeeController {
	Manager mg;
	List<Manager> man=new ArrayList<Manager>();
	public List addEmployee()
	{
		super.addEmployee();
		
		mg = new Manager();
		Scanner  sc = new Scanner(System.in);
		System.out.println("Enter dept");
		String dept = sc.next();
		mg.setDept(dept);
		System.out.println("Enter the Project name ");
		String project_name = sc.next();
		mg.setProject_name(project_name);
		System.out.print("Manager details updated");
		mg.setEmployee(emp);
		man.add(mg);
		return man;
		
	}
	public void viewManager(List man)
	{
		
		man.forEach(System.out::println);
			
	}
}