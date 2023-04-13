package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import engine.game.Player;

import java.util.List;

public class FlashHeal extends Ability {
    public FlashHeal(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 13;
    static int value = 70;
    static int cost = 80;
    static String name = "3) Flash Heal: ";
    static String info = "Heal yourself or an ally for " + value + " health. Costs " + cost + " mana.";
    static boolean targetsEnemies = false;
    static boolean targetsAllies = true;
    static boolean targetSelf = true;

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){
        System.out.print(name);
        if(playerList.get(playerID).getCurrentR() >= cost){
            System.out.println(info);
            available = true;
        }else{
            System.out.println("Not enough mana.");
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
            DrainResource.call(casterID, casterID, cost, playerList);
            TouchedByLight.cast(casterID, targetID, playerList);
            finished = true;
        }

        return finished;
    }
}
