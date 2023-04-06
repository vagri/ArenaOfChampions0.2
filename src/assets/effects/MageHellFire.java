package assets.effects;

import assets.Effect;
import assets.actions.MageMastery;
import engine.game.Player;

import java.util.List;

public class MageHellFire extends Effect {
    public MageHellFire(int effectID, String name, int casterID, int targetID, int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        super(effectID, name, casterID, targetID, value, duration, abilityID, stackable, isVisible);
    }
    static int effectID = 9;
    static String name = "Mastered: Hell Fire";

    public static void add(int casterID, int abilityID, List<Player> playerList, List<Effect> effectList) {

        MageMastery.call(casterID, abilityID, effectID, name, playerList, effectList);

    }
}
