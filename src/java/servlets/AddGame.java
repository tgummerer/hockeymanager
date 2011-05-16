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
public class AddGame extends HttpServlet {


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
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp?page=games");
		Connection con = null;
		// HTML5 datetime format is jjjj-mm-ddThh:mmZ Can be directly used like this to insert it into the database.
		User user = (User)request.getSession().getAttribute("user");
		if (user != null && (user.getAccessLevel().equals("admin"))) {
			try {
				if (!request.getParameter("awayteam").equals(request.getParameter("hometeam"))) {
					con = Connection.getConnection();
					con.startConnection();

					PreparedStatement pstmt = con.prepareStatement("insert into game (hometeam, awayteam, date) values("
							+ request.getParameter("hometeam") + ", "
							+ request.getParameter("awayteam") + ", '"
							+ request.getParameter("datetime") + "')");

					pstmt.execute();
					System.out.println(pstmt.toString());
					
					request.setAttribute("success", "The game has been added.");
				} else {
					request.setAttribute("error", "A team can't play against itself");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "The game could not be added.");

			} finally {
				try {
					// Connection might not be initialized, if the user has entered the same team for home and away team
					if (con != null)
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
