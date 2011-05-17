/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author tommy
 */
public class Penalty {
	private int time;
	private String player;
    private String teamname;
    private int minutes;
    private String type;
    
    public String getTeamName() {
        return teamname;
    }

	public String getFormattedTime() {
        int seconds = time % 60;
        int minutes = time / 60;
        String strseconds;
        String strminutes;
        if (seconds < 10)
            strseconds = "0" + String.valueOf(seconds);
        else
            strseconds = String.valueOf(seconds);


        if (minutes < 10)
            strminutes = "0" + String.valueOf(minutes);
        else
            strminutes = String.valueOf(minutes);

		return strminutes + ":" + strseconds;
	}

    public int getPeriod() {
        int minutes = time / 60;
        if (minutes < 20)
            return 1;
        if (minutes < 40)
            return 2;
        return 3; // No overtime, so if minutes > 40 we are have to be in the third period
    }

    public int getTime() {
        return time;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getType() {
        return type;
    }

    public void setTeamName(String teamname) {
        this.teamname = teamname;
    }

	public void setTime(int time) {
		this.time = time;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setType(String type) {
        this.type = type;
    }

}
