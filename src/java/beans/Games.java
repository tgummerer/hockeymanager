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

/**
 *
 * @author tommy
 */
public class Games {
	
	public ArrayList<Game> games;
	
	public Games() {
		Connection con = null;
		try {
			con = Connection.getConnection();
			con.startConnection();
			
			PreparedStatement pstmt = con.prepareStatement("select h.teamname as hometeam, a.teamname as awayteam, g.gameid, g.date " +
													" from games g join team h on g.hometeam = h.teamid join team a on g.awayteam = a.teamid " +
													" order by date desc");
			pstmt.execute();
			System.out.println(pstmt);
			ResultSet rs = pstmt.getResultSet();
			games = new ArrayList<Game>();
			while (rs.next()) {
				Game game = new Game();
				game.setGameID(rs.getInt("gameid"));
				game.setHomeTeam(rs.getString("hometeam"));
				game.setAwayTeam(rs.getString("awayteam"));
				game.setDate(rs.getString("date"));
				games.add(game);
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
	
	public ArrayList<Game> getList() {
		return games;
	}
}
