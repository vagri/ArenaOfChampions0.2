package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import assets.effects.MageArcaneBlast;
import assets.effects.ThornShield;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class ArcaneShot extends Ability {
    public ArcaneShot(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 6;
    static int value = 70;
    static int cost = 70;
    static int value2 = 85;
    static int cost2 = 75;
    static String name = "1) Arcane Shot: ";
    static String name2 = "1) Arcane Blast: ";
    static String info = "Deal " + value + " damage to an enemy. Costs " + cost + " Mana.";
    static String info2 = "Deal " + value2 + " damage to an enemy. Costs " + cost2 + " Mana.";
    static boolean targetsEnemies = true;
    static boolean targetsAllies = false;
    static boolean targetSelf = false;

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){

        for (int i = 0; i < effectList.size(); i++) {
            if (effectList.get(i).getCasterID() == playerID) {
                if (effectList.get(i).getEffectID() == 6) {
                    if(effectList.get(i).getValue() == 3){//no mastery
                        System.out.print(name2);
                        if(playerList.get(playerID).getCurrentR() >= cost2){
                            System.out.println(info2);
                            available = true;
                        }else{
                            System.out.println("Not enough mana.");
                            available = false;
                        }
                    }
                }
            }
        }
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
            for (int i = 0; i < effectList.size(); i++) {
                if (effectList.get(i).getCasterID() == casterID) {
                    if (effectList.get(i).getEffectID() == 6) {
                        if(effectList.get(i).getValue() == 3){
                            DealDamage.call(casterID, targetID, value2, playerList);
                            DrainResource.call(casterID, casterID, cost2, playerList);
                            finished = true;
                            return finished;
                        }
                    }
                }
            }
            DealDamage.call(casterID, targetID, value, playerList);
            DrainResource.call(casterID, casterID, cost, playerList);
            MageArcaneBlast.add(casterID, abilityID, playerList, effectList);
            finished = true;
        }

        return finished;
    }
}
