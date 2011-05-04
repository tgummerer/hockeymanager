// Bean soup
package beans;

/**
 *
 * @author Thomas Gummerer
 */
public class User {
	
	String firstname;
	String lastname;
	String email;
	
	public User() {
		firstname = null;
		lastname = null;
		email = null;
	}
	
	public void setFirstName(String name) {
		firstname = name;
	}
	
	public void setLastName(String name) {
		lastname = name;
	}
	
	public void setEmail(String name) {
		email = name;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	
}
