package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import assets.actions.RestoreResource;
import assets.effects.MageMagicCrystal;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class MagicShard extends Ability {
    public MagicShard(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }

    static int abilityID = 7;

    static int valuea = 80;
    static int valueb = 60;
    static int value2a = 100;
    static int value2b = 80;
    static String name = "2) Magic Shard: ";
    static String info = "Restore " + valuea +" mana to yourself, or " + valueb + " mana to an ally.";
    static String name2 = "2) Magic Crystal: ";
    static String info2 = "Restore " + value2a +" mana to yourself, or " + value2b + " mana to an ally.";
    static boolean targetsEnemies = false;
    static boolean targetsAllies = true;
    static boolean targetSelf = true;

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){
        for (int i = 0; i < effectList.size(); i++) {
            if (effectList.get(i).getCasterID() == playerID) {
                if (effectList.get(i).getEffectID() == 7) {
                    if (effectList.get(i).getValue() == 3) {
                        System.out.print(name2);
                        System.out.println(info2);
                        return available;
                    }
                }
            }
        }
        System.out.print(name);
        System.out.println(info);
        return available;
    }

    public static boolean cast(int casterID, List<Player> playerList){
        int targetID = Ability.pickTarget(casterID, playerList, targetsEnemies,targetsAllies,targetSelf);

        if(targetID == -2) {
            finished = false;
        }else {
            for (int i = 0; i < effectList.size(); i++) {
                if (effectList.get(i).getCasterID() == casterID) {
                    if (effectList.get(i).getEffectID() == 7) {
                        if (effectList.get(i).getValue() == 3) {
                            if (playerList.get(targetID).getID() != playerList.get(casterID).getID()) {

                                if (playerList.get(targetID).getID() != playerList.get(casterID).getID()) {
                                    RestoreResource.call(casterID, targetID, value2b, playerList);//mastery and ally
                                } else {
                                    RestoreResource.call(casterID, targetID, value2a, playerList);//mastery and self
                                }
                            }
                            return true;
                        }
                    }
                }
            }
            if (playerList.get(targetID).getID() != playerList.get(casterID).getID()) {
                RestoreResource.call(casterID, targetID, valueb, playerList);// no mastery and ally
            } else {
                RestoreResource.call(casterID, targetID, valuea, playerList);// no mastery and self
            }
            MageMagicCrystal.add(casterID, abilityID, playerList, effectList);
            finished = true;
        }
        return finished;
    }
}
