package com.mph.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import com.mph.dao.EmployeeDao;
import com.mph.model.Employee;
import com.mph.model.Salary;

public class EmployeeController implements EmployeeInterface {
	Employee emp;
	Salary sal;
	List<Employee> emplist = new ArrayList<Employee>();
	EmployeeDao empdao = new EmployeeDao();
	public void empCreation(){
		emp = new Employee();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Eid");
		int eno = sc.nextInt();
		emp.setEid(eno);
		System.out.println("Enter the Ename ");
		String enam = sc.next();
		emp.setEname(enam);

		sal = new Salary();

		System.out.println("Enter basic Salary");
		int basic = sc.nextInt();
		sal.setBasic(basic);		
		sal.setDa(basic);		
		sal.setHra(basic);		
		sal.setPf(basic);
		sal.setGross(basic, sal.getDa(), sal.getHra());
		sal.setNet(sal.getGross(), sal.getPf());
		emp.setSalary(sal);
		
	}
	public List addEmployee() {
		empCreation();
		//emplist.add(emp);
		empdao.insertEmp(emp);
		return emplist;

	}

	public void viewEmployee(List emplist) {

		//emplist.forEach(System.out::println);
		empdao.viewEmp();
	}

	public void sortByName(List emplist)

	{
		Collections.sort(emplist,Employee.nameComparator);
		emplist.forEach((lis)->System.out.println(lis));
		
	}
	public void sortByFname(List emplist)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Letter to be searched");
		char c = sc.next().charAt(0);
		String s=Character.toString(c);	
		String s1=s.toLowerCase();
		String s2=s.toUpperCase();
		Predicate<Employee> p1=emp1->(emp1.getEname().startsWith(s1)||emp1.getEname().startsWith(s2));
		emplist.stream().filter(p1).sorted(Comparator.comparing(Employee::getEname)).forEach(System.out::println);
	}
	public void empSerialization(List empList)
	{
		try {
			 FileOutputStream fos = new FileOutputStream("employeeData");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(emplist);
	            oos.close();
	            fos.close();
			
			System.out.println("Data is successfully serialized");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Finally");
			
		
	}
		
	}
	public void empDeserialization()
	{
		 List<Employee> employees = new ArrayList<>();
		try {
			 FileInputStream fos = new FileInputStream("employeeData");
	            ObjectInputStream oos = new ObjectInputStream(fos);
	            employees = (List) oos.readObject();
	            oos.close();
	            fos.close();
			
	            employees.forEach(System.out::println);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Finally");
			
		
	}
		
	}
	public void insertEmployeeProc()
	{
		empCreation();
		empdao.insertEmpProc(emp);
		
	}
	public void rsmd()
	{
		empdao.rsmd();
	}
	@Override
	public void type_forward_only_rs() {
		empdao.type_forward_only_rs();
		
	}
	@Override
	public void type_scroll_insensitive_rs() {
		empdao.type_scroll_insensitive_rs();
		
	}
	public void type_scroll_sensitive_rs() {
		empdao.type_scroll_sensitive_rs();
		
	}
	@Override
	public void batchUpdate() {
		empdao.batchUpdate();
		
	}

}