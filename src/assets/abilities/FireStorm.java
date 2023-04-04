package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import assets.effects.Bleed;
import assets.effects.Burn;
import assets.effects.Casting;
import assets.effects.ThornShield;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class FireStorm extends Ability {
    public FireStorm(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 9;

    static int value = 120;
    static int value2 = 30;
    static int cost = 140;
    static boolean stackable = true;
    static int duration = 1;
    static int duration2 = 1;
    static String name = "4) Fire Storm: ";
    static String info = "Start casting for the next round. If not interrupted, deal " + value +" damage to all enemies and burn them for " + value2 +" damage next round.";
    static String info2 = "Costs " + cost +" mana. If interrupted, gain back the mana.";
    static boolean targetsEnemies = true;
    static boolean targetsAllies = false;
    static boolean targetSelf = false;

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){
        System.out.print(name);
        if(playerList.get(playerID).getCurrentR() >= cost){
            System.out.println(info);
            System.out.println(info2);
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
            Casting.add(casterID, duration, abilityID, playerList, effectList);
            DrainResource.call(casterID, casterID, cost, playerList);
            System.out.println("You begin casting Fire Storm!");
            finished = true;
        }

        return finished;
    }

    public static void finishCast(int casterID, List<Player> playerList){

        for(int i = 0 ; i < effectList.size();i++){
            if(effectList.get(i).getCasterID() == casterID) {
                if(effectList.get(i).getEffectID() == 5){
                    effectList.remove(i);
                }
            }
        }
        System.out.println("You have finished casting the Fire Storm!");
        for(int i = 0; i < playerList.size();i++){
            if(playerList.get(i).getTeamID() != playerList.get(casterID).getTeamID()){
                DealDamage.call(casterID, i, value, playerList);
                Burn.add(casterID, i, value2, duration2,abilityID, stackable, playerList, effectList);
            }
        }
    }

    public static void checkBurn(int casterID,  List<Player> playerList){
        for(int i = 0;i<effectList.size();i++) {
            if(effectList.get(i).getCasterID() == casterID){
                if(effectList.get(i).getEffectID() == 0){
                    Burn.damage(casterID, effectList.get(i).getTargetID(), value, playerList);
                }
            }
        }
    }

}
