// Bean soup
package beans;

/**
 *
 * @author Thomas Gummerer
 */
public class User {
	
	String firstname;
	String lastname;
	String username;
	
	public User() {
		firstname = null;
		lastname = null;
		username = null;
	}
	
	public void setFirstName(String name) {
		firstname = name;
	}
	
	public void setLastName(String name) {
		lastname = name;
	}
	
	public void setUserName(String name) {
		username = name;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public String getUserName() {
		return username;
	}
	
	
	
}
