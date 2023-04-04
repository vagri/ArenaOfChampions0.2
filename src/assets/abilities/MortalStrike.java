package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.RestoreResource;
import engine.game.Player;

import java.util.List;

public class MortalStrike extends Ability {

    public MortalStrike(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }

    static int abilityID = 1;


    static int value = 40;

    static int value2 = 20;
    static String name = "1) Mortal Strike: ";
    static String info = "Deal " + value + " damage to an enemy. Generates " + value2 + " rage.";
    static boolean targetsEnemies = true;
    static boolean targetsAllies = false;
    static boolean targetSelf = false;

    static boolean available = true;
    static boolean finished = false;

    public static boolean checkAvailability(int playerID, List<Player> playerList){
        System.out.print(name);
        System.out.println(info);

        return available;
    }

    public static boolean cast(int casterID, List<Player> playerList){

        int targetID = Ability.pickTarget(casterID, playerList, targetsEnemies,targetsAllies,targetSelf);

        if(targetID == -2) {
            finished = false;
        }else{
            DealDamage.call(casterID, targetID, value, playerList);
            RestoreResource.call(casterID, casterID, value2, playerList);
            finished = true;
        }
        if(!playerList.get(targetID).isDead()){
            GrieviousWounds.cast(casterID, targetID, playerList);
        }

        return finished;
    }
}
