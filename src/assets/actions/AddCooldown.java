package assets.actions;

import assets.Action;
import assets.Effect;
import assets.effects.Injured;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class AddCooldown extends Action {
    public AddCooldown(int effectID, String name) {
        super(effectID, name);
    }
    static int actionID = 4;

    public static void call(int casterID, int value, int abilityPos, List<Player> playerList){
        int[] abilityCDs = new int[5];

        abilityCDs = playerList.get(casterID).getCDRemaining();
        abilityCDs[abilityPos] = value;
        playerList.get(0).setCDRemaining(abilityCDs);
    }
}
