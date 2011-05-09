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
    String accesslevel;
	
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

	public void setAccessLevel(String level) {
        accesslevel = level;
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

    public String getAccessLevel() {
		System.out.println("accesslevel");
        return accesslevel;
    }
}
