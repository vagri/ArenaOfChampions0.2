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

    static int abilityID = 8;
    static int value1 = 40;
    static int value2 = 60;
    static boolean stackable = false;
    static int duration = 2;
    static String name = "3) Mana Shield: ";
    static String info = "Use " + value1+ " mana, to shield yourself for that damage for the next " + duration +" rounds. Enemies attacking the mage will take damage equal to the amount of shield broken.";
    static String name2 = "3) Mana Barrier: ";
    static String info2 = "Use " + value2+ " mana, to shield yourself for that damage for the next " + duration +" rounds. Enemies attacking the mage will take damage equal to the amount of shield broken.";
    static boolean targetsEnemies = false;
    static boolean targetsAllies = false;
    static boolean targetSelf = true;

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){
        for (int i = 0; i < effectList.size(); i++) {
            if (effectList.get(i).getCasterID() == playerID) {
                if (effectList.get(i).getEffectID() == 6) {
                    if (effectList.get(i).getValue() == 3) {
                        if(playerList.get(playerID).getCurrentR() >= value2){
                            System.out.println(info2);
                            available = true;
                        }else{
                            System.out.println("Not enough mana.");
                            available = false;
                        }
                        return available;
                    }
                }
            }
        }

        System.out.print(name);
        if(playerList.get(playerID).getCurrentR() >= value1){
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

        for (int i = 0; i < effectList.size(); i++) {
            if (effectList.get(i).getCasterID() == casterID) {
                if (effectList.get(i).getEffectID() == 6) {
                    if (effectList.get(i).getValue() == 3) {
                        if(targetID == -2) {
                            finished = false;
                        }else{
                            ThornShield.add(casterID, targetID, value2, duration,abilityID, stackable, playerList, effectList);
                            DrainResource.call(casterID, casterID, value2, playerList);
                            finished = true;
                        }

                        return finished;
                    }
                }
            }
        }
        if(targetID == -2) {
            finished = false;
        }else{
            ThornShield.add(casterID, targetID, value1, duration,abilityID, stackable, playerList, effectList);
            DrainResource.call(casterID, casterID, value1, playerList);
            finished = true;
        }
        return finished;
    }
}
