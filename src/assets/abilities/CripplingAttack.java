package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import assets.actions.HealHealth;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class CripplingAttack extends Ability {
    public CripplingAttack(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }

    static int abilityID = 2;

    static int value = 50;
    static int value2 = 70;
    static int cost = 25;
    static String name = "3) Crippling Attack: ";
    static String info = "Deal " + value  + " damage to an enemy. If the target is bleeding, deal " + value2 + " damage instead. Costs " + cost + " rage.";
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
            for(int i = 0;i<effectList.size();i++) {
                if (effectList.get(i).getTargetID() == targetID) {
                    if (effectList.get(i).getEffectID() == 0) {
                        value = value2;
                    }
                }
            }
            DealDamage.call(casterID, targetID, value, playerList);
            DrainResource.call(casterID, casterID, cost, playerList);
            finished = true;
        }
        if(!playerList.get(targetID).isDead()){
            GrieviousWounds.cast(casterID, targetID, playerList);
        }

        return finished;
    }
}
