<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.EmployeDAO"%>
<%@page import="beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dao.SkillMasterDAO"%>
<%@ page import="beans.SkillMaster"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String employeeid = request.getParameter("employeeid");

	System.out.println("employeeid==" + employeeid);
	int eid = Integer.parseInt(employeeid);
	System.out.println("eid==" + eid);
	Employee emp = EmployeDAO.getByEmployeeId(eid);
	%>
	<%
	List<SkillMaster> list = SkillMasterDAO.getALLskill();
	request.setAttribute("list", list);

	System.out.println("list=====" + list);
	%>
	<%
	List<Integer> skill = SkillMasterDAO.getAllcheckedskill(emp.getEmployeeid());

	for (int i = 0; i < skill.size(); i++) {
		SkillMaster skillmaster = list.get(i);
		System.out.print("skillid===" + skill);
	}
	%>

	<center>
		<form action="add-employee.jsp" method="post">
			<table>

				<h1>UPDATE EMPLOYEE</h1>
				<tr>
					<td><input type="hidden" name="employeeid"
						value="<%=emp.getEmployeeid()%>"></td>
				</tr>
				<tr>
					<td><br> <br> <lable> <b>NAME:-</b></lable></td>
					<td><br> <br> <input type="text" name="name"
						value="<%=emp.getName()%>" placeholder="name enter....." required />
				</tr>

				<tr>
					<td><br> <br> <lable> <b>BIRTHDATE:-</b></lable></td>
					<td><br> <br> <input type="date" name="birthdate"
						value="<%=emp.getBirthdate()%>" required></td>
				</tr>

				<tr>
					<td><br> <br> <lable> <b>ADDRESS:-</b></lable></td>
					<td><br> <br> <input type="text" name="address"
						value="<%=emp.getAddress()%>" maxlength="150" required></input>
				</tr>

				<tr>
					<td><br> <br> <lable> <b>GENDER:-</b></lable></td>
					<%
					if (emp.getGender() == 1) {
					%>
					<td><br> <br> <input type="radio" name="gender"
						value="1" checked>MALE</input> <input type="radio" name="gender"
						value="2">FE-MALE</input></td>
				</tr>
				<%
				} else if (emp.getGender() == 2) {
				%>
				<td><br> <br> <input type="radio" name="gender"
					value="1">MALE</input> <input type="radio" name="gender" value="2"
					checked>FE-MALE</input></td>
				<%
				}
				%>
				</tr>

				<tr>
					<td><br> <br> <lable> <b>SALARY:-</b></lable></td>
					<td><br> <br> <input type="text" name="salary"
						value="<%=emp.getSalary()%>" maxlength="9" required /></td>
				</tr>

				<tr>
					<td><br> <br> <lable> <b>Skill:-</b></lable></td>
					<td>
					<%
					
					for(int i=0; i<list.size(); i++){
						System.out.print(list.get(i));
						SkillMaster skillMaster = list.get(i);
						String flag="  ";
						
						for(int j=0; j<skill.size(); j++){
							Integer eskill_id= skill.get(j);
							if(skillMaster.getSkillmasterid()==skill.get(j)){
								
								flag="checked";
								break;
							}
							
					
					%>
<%-- 						<input type="checkbox" name="skillname" value="<%=eskill_id%> " /> --%>
<%-- 						<%=eskill_id %> --%>
					<%}
						%>
<%-- 						[<%=skillMaster.getSkillmasterid()%>] --%>
						<input type="checkbox" name="skillname" value="<%=skillMaster.getSkillmasterid()%> " <%=flag %> />
						<%=skillMaster.getSkillname() %>
						<%
					}
					%>
					
					</td>
				</tr>
				<tr>
					<td><br> <br> <input type="submit"
						value="UPDATE EMPLOYEE"></td>

				</tr>
				<tr>
				</tr>

			</table>
		</form>
	</center>
</body>
</html>