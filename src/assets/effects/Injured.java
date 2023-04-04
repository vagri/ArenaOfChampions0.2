package assets.effects;

import assets.Effect;
import assets.actions.DealDamage;
import engine.game.Player;

import java.util.List;

public class Injured extends Effect{

    public Injured(int effectID, String name, int casterID, int targetID, int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        super(effectID, name, casterID, targetID, value, duration, abilityID, stackable, isVisible);
    }
    static int effectID = 2;
    static String name = "Injured";


    public static void update(int casterID, int targetID, int amount, int duration, int abilityID, boolean stackable, List<Player> playerList, List<Effect> effectList) {

        for (int i = 0; i < effectList.size(); i++) {
            if (effectList.get(i).getTargetID() == targetID) {
                if (effectList.get(i).getEffectID() == effectID) {
                    effectList.remove(i);           // we remove the injured effect
                }
            }
        }

        if (playerList.get(targetID).getCurrentHP() < (playerList.get(targetID).getMaxHP()*0.25)){ //and we add it again, if the target is still injured
            effectList.add(new Effect(effectID, name, casterID, targetID, amount, duration, abilityID, stackable, true));
        }

    }
}
