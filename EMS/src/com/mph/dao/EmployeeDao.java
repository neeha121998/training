package com.mph.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.mph.model.Employee;
import com.mph.util.MyDBConnection;

public class EmployeeDao {
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	public void insertEmp(Employee emp)
	{
		try {
			con=MyDBConnection.getDBConnection();
			ps=con.prepareStatement("insert into mphemp values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, emp.getEid());
			ps.setString(2, emp.getEname());
			ps.setInt(3, emp.getSalary().getBasic());
			ps.setInt(4, emp.getSalary().getDa());
			ps.setInt(5, emp.getSalary().getHra());
			ps.setInt(6, emp.getSalary().getPf());
			ps.setInt(7, emp.getSalary().getGross());
			ps.setInt(8, emp.getSalary().getNet());
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + "   inserted successfully !!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void viewEmp()
	{
		try {
			con=MyDBConnection.getDBConnection();
			st=con.createStatement();
			rs=st.executeQuery("select * from mphemp");
			while(rs.next())
			{
				System.out.println("Id -"+rs.getInt(1)+" Name -"+rs.getString(2)+" Basic Salary-"+rs.getInt(3)+
				" Da -"+rs.getInt(4)+" Hra -"+rs.getInt(5)+" Pf -"+rs.getInt(6)+" Gross -"+rs.getInt(7)+" Net -"+rs.getInt(8));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void insertEmpProc(Employee emp)
	{
		con=MyDBConnection.getDBConnection();
		try {
			CallableStatement cs=con.prepareCall("{call emp_proc(?,?,?,?,?,?,?,?)}");
			cs.setInt(1, emp.getEid());
			cs.setString(2, emp.getEname());
			cs.setInt(3, emp.getSalary().getBasic());
			cs.setInt(4, emp.getSalary().getDa());
			cs.setInt(5, emp.getSalary().getHra());
			cs.setInt(6, emp.getSalary().getPf());
			cs.setInt(7, emp.getSalary().getGross());
			cs.setInt(8, emp.getSalary().getNet());
			cs.execute();
			System.out.println("Record inserted successfully using procedure");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void rsmd()
	{
		con = MyDBConnection.getDBConnection();
		HashMap<Long, HashMap<String, Object>> hmap = new HashMap<Long, HashMap<String,Object>>();
		try {
			st = con.createStatement();
			rs =st.executeQuery("select * from mphemp");
			
			ResultSetMetaData md = rs.getMetaData();
			System.out.println(md.getColumnCount());
			while(rs.next())
			{
				HashMap<String, Object> row = new HashMap<String, Object>();
				for(int i=1 ; i<=md.getColumnCount();i++)
				{
					row.put(md.getColumnName(i), rs.getObject(i));	
					
					
				}
				hmap.put(rs.getLong("eid"), row);
			}
			System.out.println(hmap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void type_forward_only_rs()
	{
		try {
			con=MyDBConnection.getDBConnection();
			ps=con.prepareStatement("select * from mphemp",ResultSet.TYPE_FORWARD_ONLY);
			rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Id -"+rs.getInt(1)+" Name -"+rs.getString(2)+" Basic Salary-"+rs.getInt(3)+
				" Da -"+rs.getInt(4)+" Hra -"+rs.getInt(5)+" Pf -"+rs.getInt(6)+" Gross -"+rs.getInt(7)+" Net -"+rs.getInt(8));
				
				
			}
			System.out.println(rs.getType());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void type_scroll_insensitive_rs()
	{
		try {
			con=MyDBConnection.getDBConnection();
			ps=con.prepareStatement("select * from mphemp",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Id -"+rs.getInt(1)+" Name -"+rs.getString(2)+" Basic Salary-"+rs.getInt(3)+
				" Da -"+rs.getInt(4)+" Hra -"+rs.getInt(5)+" Pf -"+rs.getInt(6)+" Gross -"+rs.getInt(7)+" Net -"+rs.getInt(8));
				
				
			}			
			System.out.println(rs.getType());

			System.out.println("Move Cursor to Ist position");
			rs.first();
			System.out.println(rs.getString("ename"));
			
			System.out.println("Move Cursor to Last position");
			rs.last();
			System.out.println(rs.getString("ename"));
			
			System.out.println("Move Cursor to previous position");
			rs.previous();
			System.out.println(rs.getString("ename"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void type_scroll_sensitive_rs()
	{
		try {
			con=MyDBConnection.getDBConnection();
			ps=con.prepareStatement("select eid,ename,basic,da,hra,pf,gross,net from mphemp where eid=2",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Id -"+rs.getInt(1)+" Name -"+rs.getString(2)+" Basic Salary-"+rs.getInt(3)+
				" Da -"+rs.getInt(4)+" Hra -"+rs.getInt(5)+" Pf -"+rs.getInt(6)+" Gross -"+rs.getInt(7)+" Net -"+rs.getInt(8));
				
				
			}			
			System.out.println(rs.getType());
			rs.first();
			//rs.moveToInsertRow();
			//rs.updateInt("eid",6);
			while (rs.next()) {
			
		    rs.updateInt("eid", 2);    	 
			rs.updateString("ename", "def");
			rs.updateInt("pf", 500);
			rs.updateRow();
			} 
			/*rs.updateInt("basic", 33000);
			rs.updateInt("da", 300);
			rs.updateInt("hra", 300);
			rs.updateInt("pf", 300);
			rs.updateInt("gross", 3300);
			rs.updateInt("net", 3300);*/
			//rs.insertRow();
			
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("Id -"+rs.getInt(1)+" Name -"+rs.getString(2)+" Basic Salary-"+rs.getInt(3)+
				" Da -"+rs.getInt(4)+" Hra -"+rs.getInt(5)+" Pf -"+rs.getInt(6)+" Gross -"+rs.getInt(7)+" Net -"+rs.getInt(8));
				
				
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void batchUpdate()
	{
		con=MyDBConnection.getDBConnection();
		try {
			st=con.createStatement();
			st.addBatch("Update mphemp set ename='Nitin' where ename='Varun'");
			st.addBatch("Update mphemp set basic=55000 where eid=4");
			st.addBatch("Update mphemp set eid=3 where ename='abc'");
			int[] count=st.executeBatch();
			for(int i=0;i<count.length;i++)
			{
				System.out.println(count[i] +"times updated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}
