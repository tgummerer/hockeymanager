/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Player;
import beans.User;
import db.Connection;
import java.io.IOException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
		// HTML5 datetime format is jjjj-mm-ddThh:mmZ
		User user = (User)request.getSession().getAttribute("user");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
	
		if (user != null && (user.getAccessLevel().equals("admin"))) {
			try {
				if (!request.getParameter("awayteam").equals(request.getParameter("hometeam"))) {
					con = Connection.getConnection();
					con.startConnection();

					PreparedStatement pstmt = con.prepareStatement("insert into game (hometeam, awayteam, date) " + 
                            " values(?, ?, ?)");
					pstmt.setInt(1, Integer.valueOf(request.getParameter("hometeam")));
				    pstmt.setInt(2, Integer.valueOf(request.getParameter("awayteam"))); 
					
					// Lots of conversions to get the time into the database
					Date date = df.parse((String)request.getParameter("datetime"));
					Calendar d = Calendar.getInstance();
					d.setTime(date);
				    pstmt.setDate(3, new java.sql.Date(d.getTimeInMillis()));

					pstmt.execute();
					
					request.setAttribute("success", "The game has been added.");
				} else {
					request.setAttribute("error", "A team can't play against itself");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "The game could not be added.");
			} catch (ParseException e) {
				e.printStackTrace();
				request.setAttribute("error", "The date is in the wrong format.");

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
