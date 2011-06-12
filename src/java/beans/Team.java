
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author tommy
 */
public class Team implements Comparable<Team> {
	
	private String teamname = null;
	private int teamid;
    private int points;

    public void Team() {
        this.points = 0;
    }

    // For ordering of the standings
    public int compareTo(Team t) {
        // Descending order
        return t.getPoints() - points;
    }
	
	public void setTeamID(int teamid) {
		this.teamid = teamid;
	}
	public void setTeamName(String name) {
		teamname = name;
	}

    public void addPoints(int points) {
        this.points += points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

	public int getTeamID() {
		return teamid;
	}
	
	public String getTeamName() {
		return teamname;
	}

    public int getPoints() {
        return points;
    }
}

