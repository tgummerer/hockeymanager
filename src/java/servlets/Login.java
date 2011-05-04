/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tommy
 */
public class Login extends HttpServlet {

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
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
		try {
			Connection con = Connection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from usertable where email = " + request.getParameter("email") + 
														" and password = sha1(" + request.getParameter("password") + ")");
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("fistname"));
				user.setLastName(rs.getString("lastname"));
				user.setEmail(request.getParameter("email"));
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			} else {
				request.setAttribute("error", "Wrong username or password");
			}

		} catch (ClassNotFoundException e) {
			// Crap something went wrong
		} catch (SQLException sqle) {
			// Something more went totally wrong.
		}
		disp.forward(request, response);
	}

}
