package assets.actions;

import assets.Action;
import assets.Effect;
import assets.effects.Injured;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class DealDamage extends Action {

    public DealDamage(int effectID, String name) {
        super(effectID, name);
    }

    static int actionID = 0;

    public static void call(int casterID, int targetID, int value, List<Player> playerList){

        value = Effect.checkPlayerEffects(casterID, targetID, value, actionID);

        playerList.get(targetID).setCurrentHP(playerList.get(targetID).getCurrentHP() - value);
        System.out.println(playerList.get(casterID).getName() + " did " + value + " damage to " + playerList.get(targetID).getName());

        Injured.update(targetID, targetID, playerList.get(targetID).getCurrentHP(), -1, -1, false, playerList, effectList);
        Player.isPlayerDead(targetID);
    }
}
