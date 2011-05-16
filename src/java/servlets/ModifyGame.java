/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import db.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tommy
 */
public class ModifyGame extends HttpServlet {


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
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp?page=modifygame");
		Connection con = null;
        if (request.getParameter("gameid") == null) {
            request.setAttribute("error", "The gameid has to be given");
        }
		User user = (User)request.getSession().getAttribute("user");
		if (request.getParameter("gameid") != null && user != null && user.getAccessLevel().equals("admin")) {
			try {
				con = Connection.getConnection();
				con.startConnection();

				PreparedStatement pstmt = con.prepareStatement("select * from game where gameid = " + request.getParameter("gameid"));

				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				if (rs.next()) {
					request.setAttribute("hometeam", rs.getString("hometeam"));
                    request.setAttribute("awayteam", rs.getString("awayteam"));
                    request.setAttribute("date", rs.getString("date").replace(" ", "T") + "Z");
				} else {
					request.setAttribute("error", "The game you want to modify was not found.");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "The game you want to modify was not found.");

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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp?page=modifygame");
		Connection con = null;
        if (request.getParameter("gameid") == null) {
            request.setAttribute("error", "The gameid has to be given");
        }
		User user = (User)request.getSession().getAttribute("user");
		
		// Set the attribute to how they are given by the user.
		request.setAttribute("hometeam", request.getParameter("hometeam"));
        request.setAttribute("awayteam", request.getParameter("awayteam"));
        request.setAttribute("date", request.getParameter("datetime"));
		
		if (request.getParameter("gameid") != null && user != null && user.getAccessLevel().equals("admin")) {
			try {
				con = Connection.getConnection();
				con.startConnection();

				PreparedStatement pstmt = con.prepareStatement("update game "
						+ " set hometeam = " + request.getParameter("hometeam") 
						+ ", awayteam = " + request.getParameter("awayteam")
						+ ", date = '" + request.getParameter("datetime")
						+ "' where gameid = " + request.getParameter("gameid"));

				pstmt.execute();
				System.out.println(pstmt.toString());
				request.setAttribute("success", "The game was successfully modified");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "The game could not be updated.");

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