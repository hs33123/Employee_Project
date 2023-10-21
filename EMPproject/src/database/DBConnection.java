/*

 NOTICE

 Liability, COPYRIGHT NetXs Solutions Inc
 ALL RIGHTS RESERVED

 This document is CONFIDENTIAL and a TRADE SECRET of NetXs Solutions Inc. The receipt or possession 
 of this document does not convey any rights to reproduce, modify, distribute or disclose its contents, 
 or to manufacture, use, or sell anything that it may describe, in whole or in part, without the 
 specific written consent of NetXs Solutions Inc.  Any use, modification, distribution or reproduction 
 of this document without the express written consent of NetXs Solutions Inc. is a violation of the 
 copyright laws and may subject you to criminal and or civil prosecution.

 */
/**********************************************************************************************
 Name of the application               ProcurePort
 Name of the code                      
 Purpose of the code                   It is used for EBean of AdditionalExpensesDetail
 Start date of code                    12:31 PM 10/4/2007
 last modification date.               12:31 PM 10/4/2007
 Coder's name.                         PJ
 Last modifier's name.                 PJ
 Version number of the code.           1.0
 Last QA Person.                       
 Last QC date.                         
 Redirected From the File              com.hdg.client.cabin.AdditionalExpensesDetail.java
 **********************************************************************************************/

package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	String dataSourceName = "java:/comp/env/test";
	private static int totalNewDBConnection = 0;
	public byte state = -1;
	public static final byte IN_USE = 1;
	public static final byte IS_FREE = 0;
	public static final byte IS_CLOSE = 2;

	
	

	CallableStatement	     callableStatement	  = null;
	Statement	             statement	          = null;
	ResultSet	             resultSet	          = null;

	
	javax.sql.DataSource dataSource = null;
	Connection connection = null;

	public DBConnection(Connection connection) throws Exception {
		this.connection = connection;

	}

	public DBConnection() throws Exception {
		getConnectionFromDataSource();
		System.out.println(" New Connection");
		totalNewDBConnection++;
	}

	public DBConnection(InitialContext initialContext) throws Exception {
		getConnectionFromDataSource(initialContext);
		totalNewDBConnection++;
	}

	private void getConnectionFromDataSource() throws Exception {
		InitialContext initialContext = null;
		try {
			initialContext = new InitialContext();
			getConnectionFromDataSource(initialContext);

		} catch (NamingException namingException) {
			Exception Exception = new Exception(namingException);
			throw Exception;
		} finally {
			try {
				if (initialContext != null)
					initialContext.close();
			} catch (Exception exception) {
				// LogWriter.logError(
				// "getConnectionFromDataSource:initialContext.close()",
				// exception);
			}
			initialContext = null;
		}
	}

	private void getConnectionFromDataSource(InitialContext initialContext) throws Exception {
		try {
			this.state = IN_USE;
			synchronized (this) {
				this.dataSource = (DataSource) initialContext.lookup(dataSourceName);
				this.connection = this.dataSource.getConnection();
				// this.connection.setAutoCommit(true);
			}
		} catch (SQLException sqlException) {
			Exception Exception = new Exception(sqlException);
			throw Exception;
		} catch (NamingException namingException) {
			Exception Exception = new Exception(namingException);
			throw Exception;
		}
	}

	public Connection getConnection() {
		return connection;
	}
	public synchronized java.util.Date getSingleDateValue(String sql) throws SQLException
	{
		this.state = IN_USE;
		java.util.Date dt = null;
		try
		{
			this.statement = connection.createStatement();
			this.resultSet = statement.executeQuery(sql);
			if (resultSet != null)
			{
				if (resultSet.next())
				{
					dt = new java.util.Date(resultSet.getTimestamp(1).getTime());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeStatementResultSet();
		}
		return dt;
	}
	public synchronized void closeStatementResultSet() throws SQLException
	{
		//this.state=IN_USE;
		if (this.resultSet != null)
			this.resultSet.close();
		this.resultSet = null;

		if (this.statement != null)
			this.statement.close();
		this.statement = null;

		if (this.callableStatement != null)
			this.callableStatement.close();
		this.callableStatement = null;
		this.state = IS_FREE;
	}
	public void close() throws SQLException
	{
		this.closeStatementResultSet();
		if (this.connection != null)
		{
			totalNewDBConnection--;
			this.connection.close();
		}
		this.connection = null;
		this.dataSource = null;		
		System.err.println("Close DBConnection-----=" + totalNewDBConnection);
		this.state = IS_CLOSE;
	}
	public void close(Object object) 
	{
		try
		{
			this.close();
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
}
