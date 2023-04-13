package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class Execution extends Ability {
    public Execution(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 3;
    static int value = 100;
    static int cost = 40;
    static String name = "4) Execution: ";
    static String info = "Deal " + value + " damage to an enemy, if they are below 25% HP. Costs " + cost + " rage.";
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

        boolean isInjured = false;

        if(targetID == -2) {//target is invalid
            finished = false;
        }else{//target is valid

            for(int i = 0;i<effectList.size();i++) {
                if (effectList.get(i).getTargetID() == targetID) {
                    if (effectList.get(i).getEffectID() == 2) {
                        isInjured = true; //find if the target is actually injured
                    }
                }
            }

            if(isInjured){
                System.out.println(playerList.get(casterID).getName() + " will now attempt to Execute " + playerList.get(targetID).getName() + "!");
                DealDamage.call(casterID, targetID, value, playerList);
                DrainResource.call(casterID, casterID, cost, playerList);
                if(!playerList.get(targetID).isDead()){
                    GrieviousWounds.cast(casterID, targetID, playerList);
                }
                finished = true; //target is indeed injured and is now propably dead
            }else{
                System.out.println("Cannot Execute a non injured target!");
                System.out.println("Pick another target!");
                finished = false; //target is not injured and the caster should choose a new target
            }
        }
        return finished;
    }

}
