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
import helpers.Helpers;
import db.Connection;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tommy
 */
public class Login extends HttpServlet {

	private SecureRandom random = new SecureRandom();

	private String nextSessionId()
	{
		return new BigInteger(130, random).toString(32);
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
		RequestDispatcher disp = null;
		if (request.getParameter("return").equals("team")) 
			disp = request.getRequestDispatcher("index.jsp?page=teams");
        else if (request.getParameter("return").equals("gamedetails"))
            disp = request.getRequestDispatcher("index.jsp?page=games");
		else
			disp = request.getRequestDispatcher("index.jsp?page=" + request.getParameter("return"));
        Connection con = null;
		try {
			con = Connection.getConnection();
            con.startConnection();

            String encryptedPassword = Helpers.encryptPassword(request.getParameter("password"));
			PreparedStatement pstmt = con.prepareStatement("select * from usertable where email = ? and password = ?");

            pstmt.setString(1, request.getParameter("email"));
            pstmt.setString(2, encryptedPassword);

			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				User user = new User();
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setEmail(request.getParameter("email"));
				user.setAccessLevel(rs.getString("access_level"));
				user.setTeamID(rs.getInt("teamid"));
				user.setUserID(rs.getInt("userid"));
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				if (request.getParameter("staylogged") != null) {
					Cookie cookie = new Cookie("u", rs.getString("userid"));
					// A long time
					cookie.setMaxAge(360000);
					response.addCookie(cookie);

					String sessionId = nextSessionId();
					Cookie security = new Cookie("s", sessionId);
					// A long time
					security.setMaxAge(360000);
					response.addCookie(security);

					pstmt = con.prepareStatement("update usertable set cookievalue = ? where userid = ?");
					pstmt.setString(1, sessionId);
					pstmt.setInt(2, rs.getInt("userid"));
					pstmt.execute();
				}
			} else {
				request.setAttribute("loginerror", "Wrong username or password");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
