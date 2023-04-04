package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import assets.effects.ThornShield;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class ArcaneShot extends Ability {
    public ArcaneShot(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 6;
    static int value = 60;
    static int cost = 60;
    static String name = "1) Arcane Strike: ";
    static String info = "Deal " + value + " damage to an enemy. Costs " + cost + " Mana.";
    static boolean targetsEnemies = true;
    static boolean targetsAllies = false;
    static boolean targetSelf = false;

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
            finished = true;
        }

        return finished;
    }
}
