package assets.actions;

import assets.Action;
import assets.Effect;
import engine.game.Player;

import java.util.List;

public class DealDamage extends Action {

    public DealDamage(int effectID, String name) {
        super(effectID, name);
    }

    static int actionID = 0;

    public static void call(int casterID, int targetID, int value, List<Player> playerList){

        value = Effect.checkPlayerEffects(casterID, targetID, value, actionID);

        playerList.get(targetID).setCurrentHP(playerList.get(targetID).getCurrentHP() - value);
        System.out.println(playerList.get(casterID).getName() + " did " + value + " damage to " + playerList.get(targetID).getName());
    }
}
