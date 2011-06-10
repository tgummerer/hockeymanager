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
public class Games {
	
	public ArrayList<Game> games;
	
	public Games() {
		Connection con = null;
		try {
			con = Connection.getConnection();
			con.startConnection();
			
			PreparedStatement pstmt = con.prepareStatement("select h.teamname as hometeam, a.teamname as awayteam, g1.gameid, g1.date, count(p1.teamid) as hscore, count(p2.teamid) as ascore " +
                    "from game g1 join game g2 on g1.gameid = g2.gameid " +
                        "join team h on g1.hometeam = h.teamid " +
                        "join team a on g2.awayteam = a.teamid " +
                        "full join score s1 on s1.gameid = g1.gameid " +
                        "left join player p1 on (p1.playerid = s1.scorer and p1.teamid = h.teamid) " +
                        "left join player p2 on (p2.playerid = s1.scorer and p2.teamid = a.teamid) " +
                    "group by h.teamname, a.teamname, g1.gameid, g1.date " +
                    "order by g1.date desc");
                    
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			games = new ArrayList<Game>();
			while (rs.next()) {
				Game game = new Game();
				game.setGameID(rs.getInt("gameid"));
				game.setHomeTeam(rs.getString("hometeam"));
				game.setAwayTeam(rs.getString("awayteam"));
				game.setDate(rs.getString("date"));
                // Check if the game has already started via the date. Show only scores if the game has started
                java.sql.Date sqldate = rs.getDate("date");
                Calendar cal = Calendar.getInstance();
                if (cal.getTime().compareTo(sqldate) >= 0){
                    game.setHomeScore(rs.getString("hscore"));
                    game.setAwayScore(rs.getString("ascore"));
                }
                else {
                    game.setHomeScore("");
                    game.setAwayScore("");
                }
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
