package assets.abilities;

import assets.Ability;
import assets.actions.DrainResource;
import assets.effects.Shield;
import assets.effects.ThornShield;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class ManaShield extends Ability{
    public ManaShield(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }

    static int abilityID = 6;
    static String name = "3) Mana Shield: ";
    static String info = "Shield yourself for 40 damage for the next 2 rounds. Enemies attacking the mage will take damage equal to the amount of shield broken. Costs 40 Mana.";
    static int value = 40;
    static int cost = 40;
    static boolean stackable = false;
    static int duration = 2;
    static boolean targetsEnemies = false;
    static boolean targetsAllies = false;
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
            ThornShield.add(casterID, targetID, value, duration,abilityID, stackable, playerList, effectList);
            DrainResource.call(casterID, casterID, cost, playerList);
            finished = true;
        }

        return finished;
    }
}
