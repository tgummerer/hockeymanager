/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author tommy
 */
public class Game {
	
	int gameid;
	String hometeam;
	String awayteam;
	String date;
	
	public void setGameID(int gameid) {
		this.gameid = gameid;
	}

	public void setHomeTeam(String name) {
		hometeam = name;
	}
	
	public void setAwayTeam(String name) {
		awayteam = name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getGameID() {
		return gameid;
	}
	
	public String getHomeTeam() {
		return hometeam;
	}

	public String getAwayTeam() {
		return awayteam;
	}

	public String getDate() {
		return date;
	}
}
