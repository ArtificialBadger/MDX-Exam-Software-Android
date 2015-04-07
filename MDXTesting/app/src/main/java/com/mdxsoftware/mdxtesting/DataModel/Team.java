package com.mdxsoftware.mdxtesting.DataModel;

import java.io.Serializable;

/**
 * Created by Isaac on 4/7/2015.
 */
public class Team implements Serializable{

    private String teamName;

    private int teamID;

    public Team(String teamName, int teamID) {
        this.teamName = teamName;
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamID() {
        return teamID;
    }
}
