package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import assets.actions.HealHealth;
import assets.actions.RestoreResource;
import engine.game.Player;

import java.util.List;

public class Bloodthirster extends Ability{
    public Bloodthirster(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }

    static int abilityID = 1;

    static int value = 35;

    static int cost = 15;
    static boolean targetsEnemies = true;
    static boolean targetsAllies = false;
    static boolean targetSelf = false;
    static String name = "2) Bloodthrister: ";
    static String info = "Deal " + value + " damage to an enemy and heal " + value + " health. Costs " + cost + " rage.";

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){
        System.out.print(name);
        if(playerList.get(playerID).getCurrentR() >= cost){
            System.out.println(info);
            available = true;
        }else{
            System.out.println("Not enough rage.");
            available = false;
        }

        return available;
    }

    public static boolean cast(int casterID, List<Player> playerList){

        int targetID = Ability.pickTarget(casterID, playerList, targetsEnemies,targetsAllies,targetSelf);

        if(targetID == -2) {
            finished = false;
        }else{
            DealDamage.call(casterID, targetID, value, playerList);
            HealHealth.call(casterID, casterID, value, playerList);
            DrainResource.call(casterID, casterID, cost, playerList);
            finished = true;
        }
        if(!playerList.get(targetID).isDead()){
            GrieviousWounds.cast(casterID, targetID, playerList);
        }

        return finished;
    }
}

