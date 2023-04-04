package assets.effects;

import assets.Effect;
import assets.actions.DealDamage;
import engine.game.Player;

import java.util.List;

public class Casting extends Effect {

    public Casting(int effectID, String name, int casterID, int targetID, int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        super(effectID, name, casterID, targetID, value, duration, abilityID, stackable, isVisible);
    }
    static int effectID = 5;
    static String name = "Casting";


    public static void add(int casterID, int duration, int abilityID, List<Player> playerList, List<Effect> effectList) {

        effectList.add(new Effect(effectID, name, casterID, casterID, 0, duration, abilityID, true, true));

    }
}
