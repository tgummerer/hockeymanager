/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author tommy
 */
public class Goal {
	private int time;
	private String scorer;
	private String assist1;
	private String assist2;
    private String teamname;
	private int goalid;
    
	// Not really needed, but gave exception when it was not here
	// Seems like a compiler bug
	public Goal() {
		super();
	}
	
	public int getGoalID() {
		return goalid;
	}
	
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

    public void setTeamName(String teamname) {
        this.teamname = teamname;
    }

	public void setTime(int time) {
		this.time = time;
	}

	public String getScorer() {
		return scorer;
	}

	public void setScorer(String scorer) {
		this.scorer = scorer;
	}

	public String getAssist1() {
		return assist1;
	}

	public void setAssist1(String assist1) {
        if (assist1.equals("null null"))
            this.assist1 = "No first assist";
        else
            this.assist1 = assist1;
	}

	public String getAssist2() {
		return assist2;
	}

	public void setAssist2(String assist2) {
        if (assist2.equals("null null"))
            this.assist2 = "No second assist";
        else
            this.assist2 = assist2;
	}
	
	public void setGoalID(int goalid) {
		this.goalid = goalid;
	}
	
}
