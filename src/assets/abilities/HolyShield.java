package assets.abilities;

import assets.Ability;
import assets.actions.DrainResource;
import assets.effects.Shield;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class HolyShield extends Ability {
    public HolyShield(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 12;
    static int value = 75;
    static int cost = 70;
    static boolean stackable = false;
    static int duration = 2;
    static String name = "2) Holy Shield: ";
    static String info = "Shield yourself or an ally for " + value + " damage, for the next " + duration + " rounds. Costs " + cost + " mana.";
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
            Shield.add(casterID, targetID, value, duration,abilityID, stackable, playerList, effectList);
            DrainResource.call(casterID, casterID, value, playerList);
            TouchedByLight.cast(casterID, targetID, playerList);
            finished = true;
        }
        return finished;
    }
}
