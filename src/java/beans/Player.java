/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author tommy
 */
public class Player {
	private int number;
	private String firstname;
	private String lastname;
	private int playerid;
    private String team;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	public String getFirstname() {
		return firstname;
	}

    public String getTeam() {
        return team;
    }

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

    public void setTeam(String team) {
        this.team = team;
    }

	public int getPlayerID() {
		return playerid;
	}

	public void setPlayerID(int playerid) {
		this.playerid = playerid;
	}
	
}
