
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author tommy
 */
public class Team {
	
	String teamname = null;
	int teamid;
	
	public void setTeamID(int teamid) {
		this.teamid = teamid;
	}
	public void setTeamName(String name) {
		teamname = name;
	}

	public int getTeamID() {
		return teamid;
	}
	
	public String getTeamName() {
		return teamname;
	}
}

