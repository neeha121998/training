package com.mph.view;

import java.util.*;
import java.util.function.BiPredicate;

import com.mph.Exception.EmployeeNotFoundException;
import com.mph.controller.*;
import com.mph.model.*;

public class MainClass {

	public static void main(String[] args) {
		EmployeeInterface ec = new EmployeeController();
		ManagerController mg = new ManagerController();
		Scanner sc = new Scanner(System.in);
		List<Employee> elist = null;
		List<Manager> mlist = null;
		String input = null;
		System.out.println("Enter username");
		String user = sc.next();
		System.out.println("Enter password");
		String pwd = sc.next();
		BiPredicate<String, String> b = (u, p) -> u.equals("admin") && p.equals("password");
		try {
			if (b.test(user, pwd)) {
				System.out.println("Welcome " + user);
				do {
					System.out.println("Please Wait Loading!!!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Enter your Choice");
					System.out.println("1.Add Employee");
					System.out.println("2.View Employee");
					System.out.println("3.Add Manager");
					System.out.println("4.View Manager");
					System.out.println("5.Employee sort By name");
					System.out.println("6.Employee sort By Fname");
					System.out.println("7.Serialization");
					System.out.println("8.Deserialization");
					System.out.println("9.Procedure Insert");
					System.out.println("10.Result Set Meta Data");
					System.out.println("11.Type Forward only");
					System.out.println("12.Type Insensitive");
					System.out.println("13.Type sensitive");
					System.out.println("14.Batch Update");
					int choice = sc.nextInt();
					switch (choice) {
					case 1:
						elist = ec.addEmployee();
						break;
					case 2:
						ec.viewEmployee(elist);
						break;
					case 3:
						mlist = mg.addEmployee();
						break;
					case 4:
						mg.viewManager(mlist);
						break;
					case 5:
						ec.sortByName(elist);
						break;
					case 6:
						ec.sortByFname(elist);
						break;
					case 7:
						ec.empSerialization(elist);
						break;
					case 8:
						ec.empDeserialization();
						break;
					case 9:
						ec.insertEmployeeProc();
						break;
					case 10:
						ec.rsmd();
						break;
					case 11:
						ec.type_forward_only_rs();
						break;
					case 12:
						ec.type_scroll_insensitive_rs();
						break;
					case 13:
						ec.type_scroll_sensitive_rs();
						break;
					case 14:
						ec.batchUpdate();
						break;
					default:
						System.out.println("Enter correct choice");

					}
					System.out.println("Do you want to continue? Y or y for Yes");
					input = sc.next();
				} while (input.equals("Y") || input.equals("y"));
				System.out.print("System exited.. Thanks for using this system");
				System.exit(0);
			} else {
				throw new EmployeeNotFoundException();
			}
		} catch (EmployeeNotFoundException enf) {
			System.out.println(enf);
		} finally {
			System.out.println("Finally Executed");
		}
	}

}
