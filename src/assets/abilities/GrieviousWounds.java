package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.effects.Bleed;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class GrieviousWounds extends Ability{
    public GrieviousWounds(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }

    static int abilityID = 0;
    static String name = "Passive: Grievious Wounds";
    static String info = "Targets struck by the Executioner have a 25% chance to bleed 20 damage for the next 2 rounds.";
    static int value = 20;
    static int chance = 75;

    static int cost = 0;
    static int duration = 2;
    static boolean stackable = false;
    static boolean targetsEnemies = true;
    static boolean targetsAllies = false;
    static boolean targetSelf = false;

    static boolean available = true;
    static boolean finished = false;

    public static void cast(int casterID, int targetID, List<Player> playerList){

        int rn = (int) Math.floor(Math.random() * 100);
        if(rn<chance){
            Bleed.add(casterID, targetID, value, duration, abilityID, stackable, playerList, effectList);
        }
    }

    public static void check(int casterID, List<Player> playerList){


        for(int i = 0;i<effectList.size();i++) {
            if(effectList.get(i).getCasterID() == casterID){
                if(effectList.get(i).getEffectID() == 0){
                    Bleed.damage(casterID, effectList.get(i).getTargetID(), value, playerList);
                }
            }
        }
    }
}
