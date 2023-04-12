package engine.game;

import assets.Ability;
import assets.Champion;
import assets.Effect;
import assets.abilities.FireStorm;
import assets.actions.RestoreResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static assets.Effect.effectList;

public class Game extends Player{

    public Game(int ID, String name, int teamID, int championID, Integer maxHP, Integer currentHP, Integer maxR, Integer currentR ,String resource, int CDRemaining[], Boolean isDead) {
        super(ID, name, teamID, championID, maxHP, currentHP, maxR, currentR, resource, CDRemaining, isDead);
    }

    static List<Player> playerList = new ArrayList<>();
    static List<Champion> championList = new ArrayList<>();

    static int roundCounter = 0;
    static int lastPlayerID;
    static int lastTeamID;

    public static void startGame(int mode){
        championList = Champion.loadStats();
        System.out.println("Loading...");
        playerList = Player.loadStats(mode, championList);


        System.out.println("Starting Game...");

        lastPlayerID = mode*2;
        lastTeamID = 2;

        do{
            round(mode);
        }while(Player.isTeamDead() == 0);

        if(Player.isTeamDead() == 3){
            System.out.println("Somehow, its a TIE!");
        }else if(Player.isTeamDead() == 1){
            System.out.println("Team 1 has been defeated!");
        } else if (Player.isTeamDead() == 2) {
            System.out.println("Team 2 has been defeated!");
        }
    }

    public static void round(int mode) {
        roundCounter++;

        System.out.println("==================={ Round " + roundCounter + "! }========================");

        long timeInMilliSeconds = 1000;
        long timestamp = System.currentTimeMillis();

        do {

        } while (System.currentTimeMillis() < timestamp + timeInMilliSeconds);

        for (int i = 0; i < playerList.size(); i++) {
            if(lastPlayerID == (mode*2)){
                System.out.println("we are at the last player whose id is= " + (mode*2-1));
                turn(1);
            }else{
                if(lastTeamID == 1){
                    System.out.println("last team was 1, so the id is= " + (lastPlayerID+mode));
                    turn(lastPlayerID+mode);
                }else{
                    System.out.println("last team was 2, so the id is= " + (lastPlayerID-mode+1));
                    turn(lastPlayerID-mode+1);
                }
            }
            timeInMilliSeconds = 1000;
            timestamp = System.currentTimeMillis();

            do {

            } while (System.currentTimeMillis() < timestamp + timeInMilliSeconds);
        }
    }

    public static void turn(int casterid){
        for(Player player : playerList){
            System.out.println(player.toString());
        }

        casterid--;

        String state = checkPlayerState(casterid);

        System.out.println("________________[ Effects ]____________________");
        Effect.update(casterid);    //lower the effects by 1 round
        lowerCD(casterid);          //lower the cooldowns as well

        if(playerList.get(casterid).isDead()){//if the caster is dead skip turn

        }else{
            System.out.println("-----------------( It is " + playerList.get(casterid).getName() + "'s turn. )-----------------");
            if(playerList.get(casterid).getResource() == "Mana"){   //if they have mana, restore some mana
                RestoreResource.call(casterid, casterid , 25, playerList);
            }

            if(state == "Stunned"){         // if they are stunned, skip turn
                System.out.println("You lose your turn, since you are stunned.");
            }else{                          // if they are okay, play normally
                Ability.choose(casterid, state, playerList);
            }
        }

        lastPlayerID = playerList.get(casterid).getID();
        lastTeamID = playerList.get(casterid).getTeamID();
    }

    public static void lowerCD(int id){
        int[] CDarray = new int[5];

        /*CDarray = playerList.get(0).getCDRemaining();
        System.out.println("CDs of " + playerList.get(0).getName());
        for(int i = 0;i<=4;i++){
                System.out.println(CDarray[i]);
        }

        CDarray = playerList.get(1).getCDRemaining();
        System.out.println("CDs of " + playerList.get(1).getName());
        for(int i = 0;i<=4;i++){
            System.out.println(CDarray[i]);
        }

        System.out.println("Normal procedure.");*/


        CDarray = playerList.get(id).getCDRemaining();
        for(int i = 0;i<=4;i++){
            if(CDarray[i] > 0) {
                CDarray[i]--;
            }
        }
        playerList.get(id).setCDRemaining(CDarray);
    }

    public static String checkPlayerState(int ID){
        for(int i = 0;i<effectList.size();i++) {                    // search the effects
            if(effectList.get(i).getTargetID() == ID){              // find the effects affecting the player
                if(effectList.get(i).getEffectID() == 5){           // if the player is casting
                    return "Casting";                               // return casting
                }
            }
        }
        return "Normal";                                            // return normal if the player can move on.
    }

    public static int whichPlayerStarts( List<Player> playerList){
        return 0;
    }
}
