/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Player;
import beans.User;
import db.Connection;
import helpers.Helpers;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tommy
 */
public class ShowTeam extends HttpServlet {


	/** 
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp?page=team");
		Connection con = null;

		try {
			con = Connection.getConnection();
			con.startConnection();

			PreparedStatement pstmt = con.prepareStatement("select teamname from team where " +
										" teamid = ('" + request.getParameter("teamid") + "')");


			pstmt.execute();
			HttpSession session = request.getSession();
			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				session.setAttribute("teamname", rs.getString("teamname"));
				PreparedStatement players = con.prepareStatement("select * from player where " +
											" teamid = (" + request.getParameter("teamid") + ")");
				players.execute();
				ResultSet rs2 = players.getResultSet();
				ArrayList<Player> playerlist = new ArrayList<Player>();
				while (rs2.next()) {
					Player player = new Player();
					player.setPlayerID(rs2.getInt("playerid"));
					player.setFirstname(rs2.getString("firstname"));
					player.setLastname(rs2.getString("lastname"));
					player.setNumber(rs2.getInt("number"));
					playerlist.add(player);
				}
				session.setAttribute("playerlist", playerlist);
			} else {
				request.setAttribute("error", "The team doesn't exist");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "The team doesn't exist.");

		} finally {
			try {
				con.closeConnection();
			} catch (SQLException e) {
				// If it can't be closed just continue.
			}
		}
	
		disp.forward(request, response);
	}

}
