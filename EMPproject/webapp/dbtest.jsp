<%@page import="java.util.Date"%>
<%@page import="java.sql.Connection"%>
<%@page import="database.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>hello</p>
	<%
	DBConnection dbConnection = null;
	Connection connection = null;
	Date currentDate = null;
	try {
		dbConnection = new DBConnection();
		//connection = dbConnection.getConnection();
		String sql = "select getDate()";
		currentDate = dbConnection.getSingleDateValue(sql);
	%>

	Current date is
	<b><%=currentDate%></b>

	<%
	} catch (Exception exception) {
	exception.printStackTrace();

	} finally {
	try {
		if (dbConnection != null) {
			dbConnection.close();
		}
		dbConnection =null;
		
	} catch (Exception exception2) {
		exception2.printStackTrace();
	}

	}
	%>
	
</body>
</html>