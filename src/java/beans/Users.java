/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author tommy
 */
public class Users {
	
	private ArrayList<User> users;
	
	public Users() {
		Connection con = null;
		try {
			con = Connection.getConnection();
			con.startConnection();
			
			PreparedStatement pstmt = con.prepareStatement("select userid, firstname, lastname " +
					"from usertable");
                    
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				users.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.closeConnection();
			} catch (SQLException e) {
				// If it can't be closed just continue.
			}
		}
	}
	
	public ArrayList<User> getList() {
		return users;
	}
}
