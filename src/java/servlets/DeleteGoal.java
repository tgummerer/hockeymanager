/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import db.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class DeleteGoal extends HttpServlet {


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
		RequestDispatcher disp = request.getRequestDispatcher("GameDetails?gameid=" + request.getParameter("gameid"));
		Connection con = null;
		HttpSession session = request.getSession();
		User user = (User)request.getSession().getAttribute("user");
		if (user != null && (user.getAccessLevel().equals("admin") ||
                (user.getAccessLevel().equals("manager") && user.getTeamID() == Integer.valueOf((String)session.getAttribute("hometeamid"))))) {			try {
				con = Connection.getConnection();
				con.startConnection();

				PreparedStatement pstmt = con.prepareStatement("delete from score " +
																"where scoreid = ?"); 

                pstmt.setInt(1, Integer.valueOf(request.getParameter("goalid")));
				pstmt.execute();
				request.setAttribute("success", "The goal has been deleted.");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Error deleting the goal.");

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
