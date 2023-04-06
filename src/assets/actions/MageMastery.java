package assets.actions;

import assets.Action;
import assets.Effect;
import engine.game.Player;

import java.util.List;


public class MageMastery extends Action {
    public MageMastery(int actionID, String name) {
        super(actionID, name);
    }
    static int actionID = 5;
    static Integer effect;

    public static void call(int casterID, int abilityID,int effectID,String effectname, List<Player> playerList, List<Effect> effectList) {



        for (int i = 0; i < effectList.size(); i++) {
            if (effectList.get(i).getCasterID() == casterID) {
                if (effectList.get(i).getEffectID() == effectID) {
                    effect = i;
                }
            }
        }
        if(effect == null){
            effectList.add(new Effect(effectID, effectname, casterID, casterID, 1, -1, abilityID, false, false));
        }else{
            effectList.get(effect).setValue(effectList.get(effect).getValue() + 1);
            if(effectList.get(effect).getValue() == 3){// if it has been mastered, show it
                System.out.println("You have " + effectname + "!");
                effectList.get(effect).setVisible(true);
            }
        }
    }
}
