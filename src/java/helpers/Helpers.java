package helpers;

import beans.User;
import db.Connection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.Cookie;

public class Helpers {
    public static String encryptPassword(String password){
        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            // Crap, no sha1 encryption. Can't happen, except for some strange server setup.
        }

        sha1.reset();
        sha1.update(password.getBytes());
        byte[] result = sha1.digest();

        StringBuilder hexString = new StringBuilder();
        for (int i=0; i<result.length; i++) {
            hexString.append(Integer.toHexString(0xFF & result[i]));
        }
        password = hexString.toString();
        return password;
    }
    
    public static User login(Cookie[] cookies) {
		Cookie u = null;
		Cookie s = null;
		if (cookies != null)
			for (int i = 0; i < cookies.length; i++) 
			{
				if (cookies[i].getName().equals("u"))
					u = cookies[i];
				if (cookies[i].getName().equals("s")) 
					s = cookies[i];
			}

		Connection con = null;

		if (u != null && s != null) {
			try {
				con = Connection.getConnection();
				con.startConnection();

				PreparedStatement pstmt = con.prepareStatement("select * from usertable where userid = ? and cookievalue = ?");

				pstmt.setInt(1, Integer.valueOf(u.getValue()));
				pstmt.setString(2, s.getValue());

				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				if (rs.next()) {
					User user = new User();
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setEmail(rs.getString("email"));
					user.setAccessLevel(rs.getString("access_level"));
					user.setTeamID(rs.getInt("teamid"));
					user.setUserID(rs.getInt("userid"));
					return user;
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
		}
		return null;
    }
}
