package engine.game;


import assets.Champion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


    public static List<Player> loadStats(int mode, List<Champion> championList) {

        for (int i = 0; i < 2; i++) {
            System.out.println("Team " + (i + 1) + ".");
            for (int j = 0; j < mode; j++) {
                System.out.println("Player " + (j + 1) + ", choose name!");
                String name = chooseName();
                System.out.println(name + ", choose champion!");
                int classID = chooseClass();
                setPlayerStats(classID, championList);

                if(i==0){
                    playerList.add(new Player(j+1, name, i+1, classID, maxHP, currentHP, maxR, currentR, resource, CDRemaining, isDead));
                }else{
                    playerList.add(new Player(j+mode+1, name, i+1, classID, maxHP, currentHP, maxR, currentR, resource, CDRemaining, isDead));
                }

            }
        }
        return playerList;
    }

    public static String chooseName(){
        Scanner reader = new Scanner(System.in);
        String name = reader.nextLine();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static int chooseClass(){
        System.out.println("0 for Executioner.");
        System.out.println("1 for Mage.");
        System.out.println("2 for Priest.");
        String classID;

        do{
            Scanner reader = new Scanner(System.in);
            classID = reader.nextLine();
            switch(classID){
                case "0":
                    System.out.println("You have chosen the Executioner");
                    return 0;
                case "1":
                    System.out.println("You have chosen the Mage");
                    return 1;
                case "2":
                    System.out.println("You have chosen the Priest");
                    return 2;
                default:
                    System.out.println("You need to choose 0 or 1, not " + classID);
                    classID = null;
            }
        }while(classID == null);
        return 0;

    }
    public static void setPlayerStats(int classID, List<Champion> championList){
        maxHP = championList.get(classID).getMaxHP();
        currentHP = maxHP;

        maxR = championList.get(classID).getMaxR();
        switch(championList.get(classID).getResource()){
            case 0:
                resource = "Mana";
                currentR = maxR;
                break;
            case 1:
                resource = "Rage";
                currentR = 0;
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

