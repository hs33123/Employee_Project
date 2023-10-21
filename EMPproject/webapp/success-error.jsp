<%@page import="dao.SkillMasterDAO"%>
<%@page import="dao.EmployeDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dao.EmployeDAO" %>
<jsp:useBean id="emp" class="beans.Employee"></jsp:useBean>
<jsp:setProperty property="*" name="emp"/>
<%@page import="dao.SkillMasterDAO" %>
<jsp:useBean id="skillmaster" class="beans.SkillMaster"></jsp:useBean>
<jsp:setProperty property="*" name="skillmaster"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String skillMasterId_st[]=request.getParameterValues("skillMasterId");
int i = EmployeDAO.insertemployee(emp,skillMasterId_st);
if(i>0){
	response.sendRedirect("success.jsp");
}
	else
	{
		response.sendRedirect("error.jsp");
}

%>

</body>
</html>