package engine.game;


import assets.Champion;

import java.util.ArrayList;
import java.util.List;

public class Player extends PlayerStats{

    public static List<Player> playerList = new ArrayList<>();

    public Player(int ID, String name,int teamID, int championID, Integer maxHP, Integer currentHP, Integer maxR, Integer currentR, String resource, int CDRemaining[], Boolean isDead) {
        super(ID, name,teamID, championID, maxHP, currentHP, maxR, currentR, resource, CDRemaining, isDead);
    }

    static int id;
    static int teamID;
    static int maxHP;
    static int currentHP;
    static int maxR;
    static int currentR;
    static String resource;
    static int[] CDRemaining = new int[5];
    static boolean isDead;


    public static List<Player> loadStats(String name, int classID, List<Champion> championList){
        setPlayerStats(classID, championList);
        generatePlayerID();
        playerList.add(new Player(id,name,teamID,classID,maxHP,currentHP,maxR,currentR,resource,CDRemaining,isDead));

        return playerList;
    }

    public static void generatePlayerID(){
            if(playerList.size() == 0){
                id = 1;
                teamID = 1;
            }else{
                id = 2;
                teamID = 2;
            }
    }

    public static void setPlayerStats(int classID, List<Champion> championList){
        maxHP = championList.get(classID).getMaxHP();
        currentHP = maxHP;
        maxR = championList.get(classID).getMaxR();
        if(championList.get(classID).getResource() == 0){
            currentR = maxR;
        }else if(championList.get(classID).getResource() == 1){
            currentR = 0;
        }

        switch(championList.get(classID).getResource()){
            case 0:
                resource = "Mana";
                break;
            case 1:
                resource = "Rage";
                break;

        }
        for (int i = 0; i < 5; i++) {
            CDRemaining[i] = 0;
        }

        isDead = false;
    }

    public static void isPlayerDead(int playerID){
        if(playerList.get(playerID).getCurrentHP() <= 0){
            playerList.get(playerID).setDead(true);
            System.out.println(playerList.get(playerID).getName() + " has been slain!");
        }
    }

    public static int isTeamDead() {
        int team = 0;   // 0 for no dead team, 3 for a tie, other for teamID
        int deadPlayersTeam1 = 0;
        int deadPlayersTeam2 = 0;
        int teamSize = 1;

        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).isDead()) {
                if (playerList.get(i).getTeamID() == 1) {
                    deadPlayersTeam1++;
                } else {
                    deadPlayersTeam2++;
                }
            }
        }

        if(deadPlayersTeam1 == teamSize && deadPlayersTeam2 == teamSize){
            team = 3;
        }else if(deadPlayersTeam1 == teamSize){
            team = 1;
        } else if (deadPlayersTeam2 == teamSize) {
            team = 2;
        }

        return team;
    }
}

