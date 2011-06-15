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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tommy
 */
public class Logout extends HttpServlet {

	
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
		RequestDispatcher disp = null;
		if (request.getParameter("return").equals("team")) 
			disp = request.getRequestDispatcher("index.jsp?page=teams");
        else if (request.getParameter("return").equals("gamedetails"))
            disp = request.getRequestDispatcher("index.jsp?page=games");
		else
			disp = request.getRequestDispatcher("index.jsp?page=" + request.getParameter("return"));
		HttpSession session = request.getSession();
		
		// Delete cookies
		Cookie u = new Cookie("u", "");
		u.setMaxAge(0);
		response.addCookie(u);
		
		Cookie s = new Cookie("s", "");
		s.setMaxAge(0);
		response.addCookie(s);
		
		Connection con = null;
		try {
			User user = (User)session.getAttribute("user");
			if (user != null) {
				con = Connection.getConnection();
				con.startConnection();

				PreparedStatement pstmt = con.prepareStatement("update usertable set cookievalue = ? where userid = ?");

				pstmt.setNull(1, java.sql.Types.LONGVARCHAR);
				pstmt.setInt(2, user.getUserID());

				pstmt.execute();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.closeConnection();
			} catch (SQLException e) {
				// If it can't be closed just continue.
			}
		}
		session.invalidate();
		// Create new session for the user
		session = request.getSession(true);
		disp.forward(request, response);
		
	}
}
