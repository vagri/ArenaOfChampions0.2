package assets.abilities;

import assets.Ability;
import assets.effects.Bleed;
import assets.effects.Blessed;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class TouchedByLight extends Ability {
    public TouchedByLight(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 10;
    static int value = 30;
    static boolean stackable = false;
    static int duration = 3;
    static String name = "Passive: Touched by Light: ";
    static String info = "Anyone affected by your heals the last " + duration + " rounds, will heal " + value + " health, when you damage an enemy.";
    static boolean targetsEnemies = false;
    static boolean targetsAllies = true;
    static boolean targetSelf = true;

    static boolean available = true;
    static boolean finished = false;

    public static void cast(int casterID, int targetID, List<Player> playerList){
        Blessed.add(casterID, targetID, value, duration, abilityID, stackable, playerList, effectList);
    }

    public static void printinfo(){
        System.out.print(name);
        System.out.println(info);
    }

    public static void check(int casterID, List<Player> playerList){

        for(int i = 0;i<effectList.size();i++) {
            if(effectList.get(i).getCasterID() == casterID){
                if(effectList.get(i).getEffectID() == 10){
                    Blessed.heal(casterID, effectList.get(i).getTargetID(), value, playerList);
                }
            }
        }
    }
}
