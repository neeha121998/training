package com.mph.controller;

import java.util.List;

public interface EmployeeInterface {
	public  List addEmployee();
	public  void viewEmployee(List elist);
	public void sortByName(List emplist);
	public void sortByFname(List emplist);
	public void empSerialization(List empList);
	public void empDeserialization();
	public void empCreation();
	public void insertEmployeeProc();
	public void rsmd();
	public void type_forward_only_rs();
	public void type_scroll_insensitive_rs();
	public void type_scroll_sensitive_rs();
	public void batchUpdate();

}
