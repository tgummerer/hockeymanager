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

/**
 *
 * @author tommy
 */
public class ChangePermissions extends HttpServlet {


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
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp?page=changeuser");
		Connection con = null;
		User user = (User)request.getSession().getAttribute("user");
		if (user != null && user.getAccessLevel().equals("admin")) {
            if (user.getUserID() != Integer.valueOf(request.getParameter("user"))) {
                try {
                    con = Connection.getConnection();
                    con.startConnection();

                    PreparedStatement pstmt = con.prepareStatement("update usertable " +
                            "set access_level = ?, teamid = ? " +
                            "where userid = ?   ");

                    pstmt.setString(1, request.getParameter("permission_level"));
                    pstmt.setInt(2, Integer.valueOf(request.getParameter("team")));
                    pstmt.setInt(3, Integer.valueOf(request.getParameter("user")));

                    pstmt.execute();
                    request.setAttribute("success", "The permissions have been updated.");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", "The permissions could not be updated.");

                } finally {
                    try {
                        con.closeConnection();
                    } catch (SQLException e) {
                        // If it can't be closed just continue.
                    }
                }
            } else {
                request.setAttribute("error", "You can't change your own access rights.");
            }
		} else {
			request.setAttribute("error", "You don't have sufficient rights to perform this operation.");
		}
		disp.forward(request, response);
	}

}
