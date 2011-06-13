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
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import helpers.DateFormatException;



/**
 *
 * @author tommy
 */
public class AddGoal extends HttpServlet {


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
		RequestDispatcher disp = request.getRequestDispatcher("GameDetails?gameid=" + request.getParameter("gameid"));
		Connection con = null;
        HttpSession session = request.getSession();
		User user = (User)request.getSession().getAttribute("user");
        // Errors
        ArrayList<String> errors = new ArrayList<String>();
	
		if (user != null && (user.getAccessLevel().equals("admin") ||
                (user.getAccessLevel().equals("manager") && user.getTeamID() == Integer.valueOf((String)session.getAttribute("hometeamid"))))) {
			try {
                con = Connection.getConnection();
                con.startConnection();
                String[] scorer = ((String)request.getParameter("scorer")).split(":");
                String[] assist1 = ((String)request.getParameter("assist1")).split(":");
                String[] assist2 = ((String)request.getParameter("assist2")).split(":");

                // Check if all players are from the same team
                if (assist1.length == 1 || (assist2.length == 1 && assist1[0].equals(scorer[0])) ||
                        (assist1[0].equals(scorer[0]) && assist2[0].equals(scorer[0]))) {
                    if (assist2.length == 1)
                        assist2 = null;

                    if (assist1.length == 1) {
                        assist1 = null;
                        assist2 = null;
                    }

                    PreparedStatement pstmt = con.prepareStatement("insert into score (scorer, assist1, assist2, time, gameid) values (?, ?, ?, ?, ?)");
                   
					pstmt.setInt(1, Integer.valueOf(scorer[1]));
                    if (assist1 != null)
                        pstmt.setInt(2, Integer.valueOf(assist1[1]));
                    else
                        pstmt.setNull(2, java.sql.Types.INTEGER);

                    if (assist2 != null)
                        pstmt.setInt(3, Integer.valueOf(assist2[1]));
                    else
                        pstmt.setNull(3, java.sql.Types.INTEGER);
					
					// Calculate time by hand
					String[] time = ((String)request.getParameter("time")).split(":");
					if (time.length != 2) {
						DateFormatException up = new DateFormatException("Wrong date format");
						throw up;
					}
						
                    pstmt.setInt(4, Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]));
					pstmt.setInt(5, Integer.valueOf(request.getParameter("gameid")));
                    
                    pstmt.execute();

                } else {
                    errors.add("The scorer and the assists have to be from the same team");
                }
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				errors.add("Wrong date format.");
			} catch (DateFormatException e) {
				errors.add("Wrong date format.");
			} finally {
				try {
					if (con != null)
						con.closeConnection();
				} catch (SQLException e) {
					// If it can't be closed just continue.
				}
			}
		} else {
            errors.add("You don't have sufficient rights to perform this operation.");
		}
        request.setAttribute("errors", errors);
		disp.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("GameDetails?gameid=" + request.getParameter("gameid"));
		disp.forward(request, response);
	}

}
