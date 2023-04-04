package assets.abilities;

import assets.Ability;
import assets.actions.DealDamage;
import assets.actions.DrainResource;
import assets.actions.RestoreResource;
import engine.game.Player;

import java.util.List;

public class MagicShard extends Ability {
    public MagicShard(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }

    static int abilityID = 7;
    static String name = "2) Magic Shard: ";
    static String info = "Restore 80 mana to yourself, or 60 mana to an ally.";
    static int value = 80;
    static int value2 = 60;
    static boolean targetsEnemies = false;
    static boolean targetsAllies = true;
    static boolean targetSelf = true;

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
            if(playerList.get(targetID).getID() != playerList.get(casterID).getID()){
                value =  value2;
            }
            RestoreResource.call(casterID, targetID, value, playerList);

            finished = true;
        }

        return finished;
    }
}
