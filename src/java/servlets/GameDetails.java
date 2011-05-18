/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import beans.Goal;
import beans.Penalty;
import beans.Player;
import db.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tommy
 */
public class GameDetails extends HttpServlet {


	/** 
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp?page=gamedetails");
		Connection con = null;
        if (request.getParameter("gameid") == null) {
            request.setAttribute("error", "The gameid has to be given");
        }
		if (request.getParameter("gameid") != null) {
			try {
				con = Connection.getConnection();
				con.startConnection();

				PreparedStatement pstmt = con.prepareStatement("select h.teamname as hometeamname, a.teamname as awayteamname, h.teamid as hometeamid, a.teamid as awayteamid" + 
                        " from game g join team h on g.hometeam = h.teamid join team a on g.awayteam = a.teamid" + 
                        " where gameid = ?");
                pstmt.setInt(1, Integer.valueOf(request.getParameter("gameid")));

				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
                int hometeamid = -1, awayteamid = -1; // Teamids
                String hometeam = "", awayteam = "";
				if (rs.next()) {
					request.setAttribute("hometeam", rs.getString("hometeamname"));
                    request.setAttribute("awayteam", rs.getString("awayteamname"));
                    hometeam = rs.getString("hometeamname");
                    awayteam = rs.getString("awayteamname");
                    hometeamid = rs.getInt("hometeamid");
                    awayteamid = rs.getInt("awayteamid");
				} else {
					request.setAttribute("error", "The game you want to view was not found.");
				}
                
                pstmt = con.prepareStatement("select s.time, p.teamid as teamid, p.firstname as scorerfirst, p.lastname as scorerlast, a1.firstname as a1first, a1.lastname as a1last, a2.firstname as a2first, a2.lastname as a2last" +
                        " from score s join player p on s.scorer = p.playerid left join player a1 on s.assist1 = a1.playerid left join player a2 on s.assist2 = a2.playerid" +
                        " where gameid = ?" +
                        " order by s.time asc");

                pstmt.setInt(1, Integer.valueOf(request.getParameter("gameid")));
                pstmt.execute();
                rs = pstmt.getResultSet();
                ArrayList<Goal> goals = new ArrayList<Goal>();
                int hometeamscore = 0, awayteamscore = 0; // Scores
                while (rs.next()) {
                    Goal goal = new Goal();
                    goal.setTime(rs.getInt("time"));
                    goal.setScorer(rs.getString("scorerfirst") + " " + rs.getString("scorerlast"));
                    goal.setAssist1(rs.getString("a1first") + " " + rs.getString("a1last"));
                    goal.setAssist2(rs.getString("a2first") + " " + rs.getString("a2last"));
                    if (rs.getInt("teamid") == hometeamid) {
                        goal.setTeamName(hometeam);
                        hometeamscore++;
                    }
                    else {
                        goal.setTeamName(awayteam);
                        awayteamscore++;
                    }

                    goals.add(goal);
                }

                request.setAttribute("hometeamscore", hometeamscore);
                request.setAttribute("awayteamscore", awayteamscore);
                request.setAttribute("hometeamid", hometeamid);
                request.setAttribute("awayteamid", awayteamid);
                request.setAttribute("goals", goals);

                pstmt = con.prepareStatement("select pim.time, pt.minutes, pt.type, p.teamid as teamid, p.firstname as firstname, p.lastname as lastname" +
                        " from penalty pim join penaltytype pt on pim.type = pt.typeid join player p on pim.player = p.playerid" +
                        " where gameid = ?" +
                        " order by pim.time asc");

                pstmt.setInt(1, Integer.valueOf(request.getParameter("gameid")));

				pstmt.execute();
                rs = pstmt.getResultSet();
                ArrayList<Penalty> penalties = new ArrayList<Penalty>();
                int hometeampim = 0, awayteampim = 0; // Penalty Minutes
                while (rs.next()) {
                    Penalty penalty = new Penalty();
                    penalty.setTime(rs.getInt("time"));
                    penalty.setMinutes(rs.getInt("minutes"));
                    penalty.setType(rs.getString("type"));
                    penalty.setPlayer(rs.getString("firstname") + " " + rs.getString("lastname"));
                    if (rs.getInt("teamid") == hometeamid) {
                        penalty.setTeamName(hometeam);
                        hometeampim += rs.getInt("minutes");
                    }
                    else {
                        penalty.setTeamName(awayteam);
                        awayteampim += rs.getInt("minutes");
                    }

                    penalties.add(penalty);
                }
                request.setAttribute("hometeampim", hometeampim);
                request.setAttribute("awayteampim", awayteampim);
                request.setAttribute("penalties", penalties);

                // Playerlist
                pstmt = con.prepareStatement("select firstname, lastname, number, teamid " +
                        " from player " + 
                        " where teamid = ? or teamid = ?");

                pstmt.setInt(1, hometeamid);
                pstmt.setInt(2, awayteamid);
				pstmt.execute();

                rs = pstmt.getResultSet();
                ArrayList<Player> players = new ArrayList<Player>();
                while (rs.next()) {
                    Player player = new Player();
                    player.setFirstname(rs.getString("firstname"));
                    player.setLastname(rs.getString("lastname"));
                    player.setNumber(rs.getInt("number"));
                    if (rs.getInt("teamid") == hometeamid) {
                        player.setTeam(hometeam);
                    }
                    else {
                        player.setTeam(awayteam);
                    }

                    players.add(player);
                }
                request.setAttribute("players", players);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
                request.setAttribute("error", "There was an error with the database");
			} finally {
				try {
					con.closeConnection();
				} catch (SQLException e) {
					// If it can't be closed just continue.
				}
			}
		} else {
			request.setAttribute("error", "You don't have sufficient rights to perform this operation.");
		}
		disp.forward(request, response);
	}

}
