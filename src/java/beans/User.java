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
	int teamid;
	
	public User() {
		firstname = null;
		lastname = null;
		email = null;
		accesslevel = null;
		teamid = -1;
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
	
	public void setTeamID(int id) {
		teamid = id;
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
        return accesslevel;
    }
	
	public int getTeamID() {
		return teamid;
	}
}
