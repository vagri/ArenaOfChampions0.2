package assets.actions;

import assets.Action;
import engine.game.Player;

import java.util.List;

public class DealDamage extends Action {

    public DealDamage(int effectID, String name, int duration, boolean stackable, boolean isVisible) {
        super(effectID, name, duration, stackable, isVisible);
    }

    static int actionID = 0;
    static boolean isVisible = false;

    public static void call(int casterID, int targetID, int value, List<Player> playerList){


        playerList.get(targetID).setCurrentHP(playerList.get(targetID).getCurrentHP() - value);
        System.out.print(playerList.get(targetID).getCurrentHP() + "");
    }
}
