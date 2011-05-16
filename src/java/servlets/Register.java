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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tommy
 */
public class Register extends HttpServlet {


	 private boolean isValidEmail(String email){  
		String exp = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
		CharSequence str = email;  
		Pattern pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE);  
		Matcher matcher = pattern.matcher(str);  
		return matcher.matches();
	 }
  

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
        ArrayList<String> errors = new ArrayList<String>();
		try {
			con = Connection.getConnection();
			con.startConnection();
			if (!request.getParameter("password").equals(request.getParameter("passwordconfirm")))
                errors.add("Passwords don't match");

            if (!isValidEmail(request.getParameter("email")))
                errors.add("The email address you entered is not valid.");

            if (errors.isEmpty()) {
                String encryptedPassword = Helpers.encryptPassword(request.getParameter("password"));
                PreparedStatement pstmt = con.prepareStatement("insert into usertable (firstname, lastname, email, password) values" + 
                                            " ('" + request.getParameter("firstname") + "', '" + request.getParameter("lastname") + 
                                            "', '" + request.getParameter("email") + "', '" + encryptedPassword + "')");


                pstmt.execute();
                request.setAttribute("success", "You have been successfully registred.");
            }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
            errors.add("The email address is already taken.");
		} finally {
			try {
				con.closeConnection();
			} catch (SQLException e) {
				// If it can't be closed just continue.
			}
		}
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
			// Set attribute, to reenter it to the register form. Convenience for the user
			request.setAttribute("firstname", request.getParameter("firstname"));
			request.setAttribute("lastname", request.getParameter("lastname"));
			request.setAttribute("email", request.getParameter("email"));
		}
		disp.forward(request, response);
	}

}
