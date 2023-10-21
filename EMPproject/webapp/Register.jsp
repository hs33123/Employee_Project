<%@page import="dao.SkillMasterDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.SkillMaster"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="database.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<center>
		<table>

			<form action="success-error.jsp" method="post">

				<tr>
					<td><br> <br> <lable> <b>NAME:-</b></lable></td>
					<td><br> <br> <input type="text" name="name"
						placeholder="name enter....." required />
				</tr>

				<tr>
					<td><br> <br> <lable> <b>BIRTHDATE:-</b></lable></td>
					<td><br> <br> <input type="date" name="birthdate"
						required></td>
				</tr>

				<tr>
					<td><br> <br> <lable> <b>ADDRESS:-</b></lable></td>
					<td><br> <br> <textarea rows="5" cols="25"
							name="address" placeholder="fulladdress...." maxlength="150"
							required></textarea>
				</tr>

				<tr>
					<td><br> <br> <lable> <b>GENDER:-</b></lable></td>
					<td><br> <br> <input type="radio" name="gender"
						value="1">MALE</input> <input type="radio" name="gender"
						value="2">FE-MALE</input></td>
				</tr>



				<tr>
					<td><br> <br> <lable> <b>SALARY:-</b></lable></td>
					<td><br> <br> <input type="text" name="salary"
						maxlength="9" required /></td>
				</tr>
			<tr>
				<td><lable>
					<b>SKILL:-</b></lable></td>
				
				
				<% 	
	  			List<SkillMaster> list = SkillMasterDAO.getALLskill();
				request.setAttribute("list", list);
			%>			
				<c:forEach var="skillmaster" items="${list}">
					<td><input type="checkbox" name="skillMasterId" value="${skillmaster.skillmasterid}"> <c:out value="${skillmaster.skillname}"/></td>
				</c:forEach>
			</tr>


			<tr>
				<td><br> <br>
					<Button type="submit">SUBMIT</Button></td>
			</tr>

		</table>
	</center>
</body>
</html>