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
import java.util.Vector;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author tommy
 */
public class Teams {
	
	private ArrayList<Team> teams;
	
	public Teams() {
		Connection con = null;
		try {
			con = Connection.getConnection();
			con.startConnection();
			
			PreparedStatement pstmt = con.prepareStatement("select teamid, teamname from team");
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			teams = new ArrayList<Team>();
			while (rs.next()) {
				Team team = new Team();
				team.setTeamID(rs.getInt("teamid"));
				team.setTeamName(rs.getString("teamname"));
				teams.add(team);
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
	
	public ArrayList<Team> getList() {
		return teams;
	}
}
