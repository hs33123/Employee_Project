package dao;

import java.beans.Statement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import dao.SkillMasterDAO;
import beans.Employee;
import beans.SkillMaster;
import database.DBConnection;

public class EmployeDAO {

	public static int insertemployee(Employee emp, String[] skillMasterId_st) {
		DBConnection dbConnection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;

		int result = 0;
		try {
			dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			pstmt = connection.prepareStatement(
					"insert into employee(name,address,gender,salary,birthdate)values(?,?,?,?,?)",
					pstmt.RETURN_GENERATED_KEYS);

			pstmt.setString(1, emp.getName());
			pstmt.setString(2, emp.getAddress());
			pstmt.setByte(3, emp.getGender());
			pstmt.setDouble(4, emp.getSalary());
			SimpleDateFormat dateformat = new SimpleDateFormat("yyy-MM-dd");
			java.util.Date utildate = dateformat.parse(emp.getBirthdate());
			java.sql.Date sqldate = new java.sql.Date(utildate.getTime());
			pstmt.setDate(5, sqldate);

			result = pstmt.executeUpdate();
			resultSet = pstmt.getGeneratedKeys();
			int empid = 0;
			if (resultSet.next()) {
				empid = resultSet.getInt(1);
				System.out.println("empid ===" + empid);
			}

			pstmt1 = connection.prepareStatement("insert into employeeskill(skillids,empid)values(?,?)");

			int skillMaterId = 0;
			for (int i = 0; i < skillMasterId_st.length; i++) {
				System.out.println("skill id=====" + skillMasterId_st[i] + " ,,," + empid);
				skillMaterId = Integer.parseInt(skillMasterId_st[i]);

				pstmt1.setInt(1, skillMaterId);
				pstmt1.setInt(2, empid);
				pstmt1.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static List<Employee> getALLemployee() {
		DBConnection dbConnection = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			pstmt = connection.prepareStatement("select * from employee");
			rst = pstmt.executeQuery();
			while (rst.next()) {
				Employee emp = new Employee();
				emp.setEmployeeid(rst.getInt("employeeid"));
				emp.setName(rst.getString("name"));
				emp.setAddress(rst.getString("address"));
				emp.setGender(rst.getByte("gender"));
				emp.setSalary(rst.getDouble("salary"));
				emp.setBirthdate(rst.getString("birthdate"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return list;
	}

	public static int deleteemployee(Employee emp) {
		DBConnection dbConnection = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			pstmt = connection.prepareStatement("delete from employee where employeeid=?");
			pstmt.setInt(1, emp.getEmployeeid());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return result;

	}

	public static int updateemployee(Employee emp,String[] skillMasterId_st) {
		DBConnection dbConnection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		
		
		int result = 0;

		try {
			dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			pstmt = connection.prepareStatement(
					"update employee set name=?,address=?,gender=?,salary=?,birthdate=? where employeeid=?");
			pstmt.setString(1, emp.getName());
			System.out.println(emp.getName());
			pstmt.setString(2, emp.getAddress());
			System.out.println(emp.getAddress());
			pstmt.setByte(3, emp.getGender());
			System.out.println(emp.getGender());
			pstmt.setDouble(4, emp.getSalary());
			System.out.println(emp.getSalary());
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utildate = dateformat.parse(emp.getBirthdate());
			java.sql.Date sqldate = new java.sql.Date(utildate.getTime());
			pstmt.setDate(5, sqldate);
			System.out.println(emp.getBirthdate());
			pstmt.setInt(6, emp.getEmployeeid());
			System.out.println(emp.getEmployeeid());

			result = pstmt.executeUpdate();
			
			

			pstmt1 = connection.prepareStatement("delete from EmployeeSkill where empid=?");

			System.out.println(emp.getEmployeeid());
			pstmt1.setInt(1,emp.getEmployeeid());
			pstmt1.executeUpdate();
			

			int empid=0;
			pstmt2 = connection.prepareStatement("insert into EmployeeSkill(skillids,empid)values(?,?)");
			
			int skillMaterId = 0;
			for (int i = 0; i < skillMasterId_st.length; i++) {
				System.out.println("skill id"+ skillMasterId_st[i] + ":::" + empid);
				skillMaterId = Integer.parseInt(skillMasterId_st[i]);

				pstmt2.setInt(1, skillMaterId);
				pstmt2.setInt(2, empid);
				pstmt2.executeUpdate();
			}
					} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt1 != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return result;
	}

	public static Employee getByEmployeeId(int employeeid) {
		DBConnection dbConnection = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Employee emp = null;
		try {
			dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			pstmt = connection.prepareStatement("select * from employee where employeeid=?");
			pstmt.setInt(1, employeeid);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				emp = new Employee();
				emp.setEmployeeid(employeeid);
				emp.setName(rst.getString("name"));
				emp.setAddress(rst.getString("address"));
				emp.setGender(rst.getByte("gender"));
				emp.setSalary(rst.getDouble("salary"));
				emp.setBirthdate(rst.getString("birthdate"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return emp;
	}

}