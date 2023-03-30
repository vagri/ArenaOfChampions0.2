package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import engine.game.Player;

import java.util.List;

public class GrieviousWounds extends Ability{
    public GrieviousWounds(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }

    static int abilityID = 0;
    static String name = "Passive: Grievious Wounds";
    static String info = "Targets struck by the Executioner have a 25% chance to bleed 20 damage for the next 2 rounds.";
    static int value = 20;
    static int chance = 25;

    static int cost = 0;
    static boolean targetsEnemies = true;
    static boolean targetsAllies = false;
    static boolean targetSelf = false;

    static boolean available = true;
    static boolean finished = false;

    public static void cast(int casterID, int targetID, List<Player> playerList){

        int rn = (int) Math.floor(Math.random() * 100);
        if(rn<chance){

        }
    }
}
