/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Player;
import beans.User;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author tommy
 */
public class AddPlayer extends HttpServlet {


	/** 
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp?page=team&teamid=" + request.getParameter("teamid"));
		Connection con = null;
		User user = (User)request.getSession().getAttribute("user");
		if (user != null && (user.getAccessLevel().equals("admin") || 
							(user.getAccessLevel().equals("manager") && 
							 user.getTeamID() == Integer.valueOf(request.getParameter("teamid"))))) {
			try {
				con = Connection.getConnection();
				con.startConnection();

				PreparedStatement pstmt = con.prepareStatement("insert into player (teamid, firstname, lastname, number) "
						+ "values" + " (" + request.getParameter("teamid") + ", '" 
						+ request.getParameter("firstname") + "', '"
						+ request.getParameter("lastname") + "', "
						+ request.getParameter("number") + ")");


				pstmt.execute();
				
				HttpSession session = request.getSession();
				ArrayList<Player> playerlist = (ArrayList<Player>)session.getAttribute("playerlist");
				Player player = new Player();
				player.setFirstname(request.getParameter("firstname"));
				player.setLastname(request.getParameter("lastname"));
				player.setNumber(Integer.valueOf(request.getParameter("number")));

				// Get ID of latest inserted player
				ResultSet rskey = pstmt.getGeneratedKeys();
				if (rskey != null && rskey.next()) {
				  player.setPlayerID(rskey.getInt(1));
				}
				
				playerlist.add(player);
				request.setAttribute("playerlist", playerlist);
				
				request.setAttribute("success", "The player has been added.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "A player with this number already exists.");

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
