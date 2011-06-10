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
    String homescore;
    String awayscore;
	
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

    public void setHomeScore(String homescore) {
        this.homescore = homescore;
    }

    public void setAwayScore(String awayscore) {
        this.awayscore = awayscore;
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

    public String getHomeScore () {
        return homescore;
    }

    public String getAwayScore () {
        return awayscore;
    }
}
