package beans;

import db.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;

public class Standings {
    
    private ArrayList<Team> teams;

    public Standings () {
        Connection con = null;
		try {
			con = Connection.getConnection();
			con.startConnection();

            HashMap<Integer, Team> teamshelper = new HashMap<Integer, Team>();

            // Make sure all teams are in the list, even if no games are played
			PreparedStatement pstmt = con.prepareStatement("select teamid, teamname from team");
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			while (rs.next()) {
				Team team = new Team();
				team.setTeamID(rs.getInt("teamid"));
				team.setTeamName(rs.getString("teamname"));
				teamshelper.put(rs.getInt("teamid"), team);
			}

			
            // Separate queries for home team wins, away team wins, home team draw and awayteamdraws
            // Home team wins
            pstmt = con.prepareStatement("select hometeam as teamid, teamname, count(*) as score " + 
                    "from (select h.teamid as hometeam, h.teamname as teamname, a.teamid as awayteam, g1.gameid, count(p1.teamid) as htscore, count(p2.teamid) as atscore " +
                          "from game g1 join game g2 on g1.gameid = g2.gameid " +
                              "join team h on g1.hometeam = h.teamid " +
                              "join team a on g2.awayteam = a.teamid " +
                              "full join score s1 on s1.gameid = g1.gameid " +
                              "left join player p1 on (p1.playerid = s1.scorer and p1.teamid = h.teamid) " +
                              "left join player p2 on (p2.playerid = s1.scorer and p2.teamid = a.teamid) " +
                          "where g1.date < now() " +
                          "group by h.teamid, h.teamname, a.teamid, g1.gameid, g1.date " +
                          "order by g1.date desc) game " +
                    "where htscore > atscore " +
                    "group by hometeam, awayteam, teamname");
			System.out.println(pstmt.toString());
			pstmt.execute();
			rs = pstmt.getResultSet();
			while (rs.next()) {
                Team team = null;
                if (teamshelper.containsKey(rs.getInt("teamid"))) {
                    team = teamshelper.get(rs.getInt("teamid"));
                    team.addPoints(rs.getInt("score") * 2);
                } else { // May only happen, if game is changed while the code is executing. Very unlikely.
                    team = new Team();
                    team.setTeamID(rs.getInt("teamid"));
                    team.setTeamName(rs.getString("teamname"));
                    team.addPoints(rs.getInt("score") * 2); // A win counts 2 points
                }
				teamshelper.put(rs.getInt("teamid"), team);
			}
            
            // Away team wins
            pstmt = con.prepareStatement("select awayteam as teamid, teamname, count(*) as score " +
                    "from (select h.teamid as hometeam, a.teamid as awayteam, a.teamname as teamname, g1.gameid, count(p1.teamid) as htscore, count(p2.teamid) as atscore " +
                          "from game g1 join game g2 on g1.gameid = g2.gameid " +
                              "join team h on g1.hometeam = h.teamid " +
                              "join team a on g2.awayteam = a.teamid " +
                              "full join score s1 on s1.gameid = g1.gameid " +
                              "left join player p1 on (p1.playerid = s1.scorer and p1.teamid = h.teamid) " +
                              "left join player p2 on (p2.playerid = s1.scorer and p2.teamid = a.teamid) " +
                          "where g1.date < now() " +
                          "group by h.teamid, a.teamname, a.teamid, g1.gameid, g1.date " +
                          "order by g1.date desc) game " +
                    "where atscore > htscore " +
                    "group by hometeam, awayteam, teamname");
			pstmt.execute();
			rs = pstmt.getResultSet();
			while (rs.next()) {
                Team team = null;
                if (teamshelper.containsKey(rs.getInt("teamid"))) {
                    team = teamshelper.get(rs.getInt("teamid"));
                    team.addPoints(rs.getInt("score") * 2);
                } else { // May only happen, if game is changed while the code is executing. Very unlikely.
                    team = new Team();
                    team.setTeamID(rs.getInt("teamid"));
                    team.setTeamName(rs.getString("teamname"));
                    team.addPoints(rs.getInt("score") * 2); // A win counts 2 points
                }
				teamshelper.put(rs.getInt("teamid"), team);
			}

            // Home team draws
            pstmt = con.prepareStatement("select hometeam as teamid, teamname, count(*) as score " +
                    "from (select h.teamid as hometeam, a.teamid as awayteam, h.teamname as teamname, g1.gameid, count(p1.teamid) as htscore, count(p2.teamid) as atscore " +
                          "from game g1 join game g2 on g1.gameid = g2.gameid " +
                              "join team h on g1.hometeam = h.teamid " +
                              "join team a on g2.awayteam = a.teamid " +
                              "full join score s1 on s1.gameid = g1.gameid " +
                              "left join player p1 on (p1.playerid = s1.scorer and p1.teamid = h.teamid) " +
                              "left join player p2 on (p2.playerid = s1.scorer and p2.teamid = a.teamid) " +
                          "where g1.date < now() " +
                          "group by h.teamid, h.teamname, a.teamid, g1.gameid, g1.date " +
                          "order by g1.date desc) game " +
                    "where htscore = atscore " +
                    "group by hometeam, awayteam, teamname");
			pstmt.execute();
			rs = pstmt.getResultSet();
			while (rs.next()) {
                Team team = null;
                if (teamshelper.containsKey(rs.getInt("teamid"))) {
                    team = teamshelper.get(rs.getInt("teamid"));
                    team.addPoints(rs.getInt("score"));
                } else { // May only happen, if game is changed while the code is executing. Very unlikely.
                    team = new Team();
                    team.setTeamID(rs.getInt("teamid"));
                    team.setTeamName(rs.getString("teamname"));
                    team.addPoints(rs.getInt("score")); // A draw counts 1 point
                }
				teamshelper.put(rs.getInt("teamid"), team);
			}

            // Away team draws
            pstmt = con.prepareStatement("select awayteam as teamid, teamname, count(*) as score " +
                    "from (select h.teamid as hometeam, a.teamid as awayteam, a.teamname as teamname, g1.gameid, count(p1.teamid) as htscore, count(p2.teamid) as atscore " +
                          "from game g1 join game g2 on g1.gameid = g2.gameid " +
                              "join team h on g1.hometeam = h.teamid " +
                              "join team a on g2.awayteam = a.teamid " +
                              "full join score s1 on s1.gameid = g1.gameid " +
                              "left join player p1 on (p1.playerid = s1.scorer and p1.teamid = h.teamid) " +
                              "left join player p2 on (p2.playerid = s1.scorer and p2.teamid = a.teamid) " +
                          "where g1.date < now() " +
                          "group by h.teamid, a.teamname, a.teamid, g1.gameid, g1.date " +
                          "order by g1.date desc) game " +
                    "where htscore = atscore " +
                    "group by hometeam, awayteam, teamname");
			pstmt.execute();
			rs = pstmt.getResultSet();
			while (rs.next()) {
                Team team = null;
                if (teamshelper.containsKey(rs.getInt("teamid"))) {
                    team = teamshelper.get(rs.getInt("teamid"));
                    team.addPoints(rs.getInt("score"));
                } else { // May only happen, if game is changed while the code is executing. Very unlikely.
                    team = new Team();
                    team.setTeamID(rs.getInt("teamid"));
                    team.setTeamName(rs.getString("teamname"));
                    team.addPoints(rs.getInt("score")); // A draw counts 1 point
                }
				teamshelper.put(rs.getInt("teamid"), team);
			}

            Collection<Team> c = teamshelper.values();
            Iterator<Team> i = c.iterator();
			teams = new ArrayList<Team>();
            while (i.hasNext())
                teams.add(i.next());

            Collections.sort(teams);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.closeConnection();
			} catch (SQLException e) {
				// If it can't be closed just continue.
			} catch (NullPointerException e) {
                // Connection was never initialized, just do nothing.
                e.printStackTrace();
            }
		}
	}

    public ArrayList<Team> getList() {
        return teams;
    }

}
