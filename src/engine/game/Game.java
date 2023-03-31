package engine.game;

import assets.Ability;
import assets.Champion;
import assets.Effect;

import java.util.ArrayList;
import java.util.List;

public class Game extends Player{

    public Game(int ID, String name, int teamID, int championID, Integer maxHP, Integer currentHP, Integer maxR, Integer currentR ,String resource, int CDRemaining[], Boolean isDead) {
        super(ID, name, teamID, championID, maxHP, currentHP, maxR, currentR, resource, CDRemaining, isDead);
    }

    static List<Player> playerList = new ArrayList<>();
    static List<Champion> championList = new ArrayList<>();

    static int roundCounter = 0;
    static int lastPlayerID = 0;

    public static void startGame(String name1, String name2, int classID1, int classID2){
        championList = Champion.loadStats();
        System.out.println("Loading...");
        playerList = Player.loadStats(name1, classID1, championList);
        playerList = Player.loadStats(name2, classID2, championList);

        System.out.println("Starting Game...");

        do{
            round();
        }while(Player.isTeamDead() == 0);

        if(Player.isTeamDead() == 3){
            System.out.println("Somehow, its a TIE!");
        }else if(Player.isTeamDead() == 1){
            System.out.println("Team 1 has been defeated!");
        } else if (Player.isTeamDead() == 2) {
            System.out.println("Team 2 has been defeated!");
        }
    }

    public static void round(){
        roundCounter++;
        System.out.println("==================={ Round " + roundCounter + "! }========================");

        turn(playerList.get(0).getID());
        turn(playerList.get(1).getID());

    }

    public static void turn(int casterid){

        for(Player player : playerList){
            System.out.println(player.toString());
        }

        casterid--;
        System.out.println("________________[ Effects ]____________________");
        Effect.update(casterid);
        lowerCD(casterid);

        System.out.println("-----------------( It is " + playerList.get(casterid).getName() + "'s turn. )-----------------");

        Ability.choose(casterid, playerList);
    }

    public static void lowerCD(int id){
        int[] CDarray = new int[5];
        CDarray = playerList.get(id).getCDRemaining();
        for(int i = 0;i<=4;i++){
            if(CDarray[i] > 0) {
                CDarray[i] = CDarray[i]--;
            }
        }
        playerList.get(id).setCDRemaining(CDarray);
    }
}
