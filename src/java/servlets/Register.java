/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db.Connection;
import helpers.Helpers;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class PasswordsDontMatchException extends Exception {
	PasswordsDontMatchException(String message) {
		super(message);
	}
}

/**
 *
 * @author tommy
 */
public class Register extends HttpServlet {


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
		RequestDispatcher disp = request.getRequestDispatcher("index.jsp?page=register");
		Connection con = null;
		try {
			con = Connection.getConnection();
			con.startConnection();
			if (!request.getParameter("password").equals(request.getParameter("passwordconfirm"))) {
				PasswordsDontMatchException up = new PasswordsDontMatchException("Passwords don't match.");
				throw up; // Not funny
			}
			
			String encryptedPassword = Helpers.encryptPassword(request.getParameter("password"));
			PreparedStatement pstmt = con.prepareStatement("insert into usertable (firstname, lastname, email, password) values" + 
                                        " ('" + request.getParameter("firstname") + "', '" + request.getParameter("lastname") + 
                                        "', '" + request.getParameter("email") + "', '" + encryptedPassword + "')");

			System.out.print(pstmt.toString());
            pstmt.execute();
			request.setAttribute("success", "You have been successfully registred.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "The email address is already taken");
			
		} catch (PasswordsDontMatchException e) {
			request.setAttribute("error", "The passwords don't match");
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
