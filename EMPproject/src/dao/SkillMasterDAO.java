package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import beans.SkillMaster;
import database.DBConnection;

public class SkillMasterDAO {
	
	public static List<SkillMaster> getALLskill() {
		DBConnection dbConnection = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<SkillMaster> list = new ArrayList<SkillMaster>();
		
		try {

			dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			pstmt = connection.prepareStatement("select * from skillmaster");
			rst = pstmt.executeQuery();
			while (rst.next()) {
				SkillMaster skillmaster = new SkillMaster();
				skillmaster.setSkillmasterid(rst.getInt("skillMasterId"));
				skillmaster.setSkillname(rst.getString("skillname"));
				list.add(skillmaster);
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
	 
      public static List<Integer> getAllcheckedskill(int empid){
    	  DBConnection dbConnection = null;
  		PreparedStatement pstmt = null;
  		ResultSet rst = null;
  		
    	  List<Integer> list = new ArrayList<Integer>();
    	  try {
    		  dbConnection = new DBConnection();
    		  Connection connection = dbConnection.getConnection();
    		  pstmt = connection.prepareStatement("select * from EmployeeSkill Where empid=?");
    		  pstmt.setInt(1, empid);
    		  rst = pstmt.executeQuery();
    		  while (rst.next()) {
				list.add(rst.getInt("skillids"));
			}
    		 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  finally {
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
    	  
      }


