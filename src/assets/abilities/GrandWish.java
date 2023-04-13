package assets.abilities;

import assets.Ability;
import assets.actions.AddCooldown;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import assets.actions.HealHealth;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class GrandWish extends Ability {
    public GrandWish(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 14;
    static int value = 150;
    static int cost = 100;
    static int cooldown = 6;
    static String name = "4) Grand Wish: ";
    static String info = "Heal your team for " + value + " health, split on all allies. Costs " + cost + " mana.";
    static boolean targetsEnemies = false;
    static boolean targetsAllies = true;
    static boolean targetSelf = true;

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){
        int[] abilityCDs = playerList.get(playerID).getCDRemaining();

        System.out.print(name);

        if(abilityCDs[4] > 0){
            System.out.println("On a " + abilityCDs[4] + " round cooldown.");
            available = false;
        }else if(playerList.get(playerID).getCurrentR() >= cost){
            System.out.println(info);
            available = true;
        }else{
            System.out.println("Not enough mana.");
            available = false;
        }

        return available;
    }

    public static boolean cast(int casterID, List<Player> playerList){

        System.out.println("You call upon the heavens to aid your team!");

        int teamsize = 0;
        for(int i = 0 ; i < playerList.size();i++){
            if (playerList.get(i).getTeamID() == playerList.get(casterID).getTeamID() && !playerList.get(i).isDead()) {
                teamsize++; //find how many teammates are alive
            }
        }

        double splitvalue = value/teamsize; //get the value each teamate will heal, with the the total being the value

        for(int i = 0 ; i < effectList.size();i++){
            if(playerList.get(i).getTeamID() == playerList.get(casterID).getTeamID() && !playerList.get(i).isDead()){
                HealHealth.call(casterID, i, (int)(splitvalue), playerList); // if this player is a teammate and alive, heal them
                TouchedByLight.cast(casterID, i, playerList);
            }
        }

        DrainResource.call(casterID, casterID, cost, playerList);
        AddCooldown.call(casterID, cooldown, 4,   playerList);

        return true;
    }
}
