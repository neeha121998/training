package com.mph.model;

public class Manager extends Employee {
	private String dept;
	private String project_name;
	private Employee employee;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(String dept, String project_name) {
		super();
		this.dept = dept;
		this.project_name = project_name;
	}
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Manager [dept=" + dept + ", project_name=" + project_name + ", " + employee + "]";
	}
	
	
	
	
	
	
	
	
	

}
