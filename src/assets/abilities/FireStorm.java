package assets.abilities;

import assets.Ability;
import assets.actions.AddCooldown;
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
    static int cost2 = 150;
    static boolean stackable = true;
    static int duration = 1;
    static int duration2 = 1;
    static int cooldown = 5;
    static String name1 = "4) Fire Storm: ";
    static String infoa1 = "Start casting for the next round. If not interrupted, deal " + value +" damage to all enemies and burn them for " + value2 +" damage next round.";
    static String infob1 = "Costs " + cost +" mana. On a " + cooldown + " round cooldown. If interrupted, gain back the mana. ";
    static String name2= "4) Hell Fire: ";
    static String infoa2 = "Instantly deal " + value +" damage to all enemies and burn them for " + value2 +" damage next round.Costs " + cost2 +" mana.";
    static String infoB2 = "On a " + cooldown + " round cooldown. If one spell is mastered, this spell is mastered too. ";

    static boolean targetsEnemies = true;
    static boolean targetsAllies = false;
    static boolean targetSelf = false;

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){
        int[] abilityCDs = playerList.get(playerID).getCDRemaining();
        int actualCost = cost;


        System.out.print(name1);



        if(abilityCDs[4] > 0){
            System.out.println("On a " + abilityCDs[4] + " round cooldown.");
            available = false;
        }else if(playerList.get(playerID).getCurrentR() >= actualCost){
            System.out.println(infoa1);
            System.out.println(infob1);
            available = true;
        }else{
            System.out.println("Not enough mana.");
            available = false;
        }


        return available;
    }

    public static boolean cast(int casterID, List<Player> playerList){


            Casting.add(casterID, duration, abilityID, playerList, effectList);
            DrainResource.call(casterID, casterID, cost, playerList);
            AddCooldown.call(casterID, cooldown, 4,   playerList);
            System.out.println("You begin casting Fire Storm!");
            finished = true;


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
                if(effectList.get(i).getEffectID() == 1){
                    Burn.damage(casterID, effectList.get(i).getTargetID(), value2, playerList);
                }
            }
        }
    }

}
