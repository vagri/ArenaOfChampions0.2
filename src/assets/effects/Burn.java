package assets.effects;

import assets.Effect;
import assets.actions.DealDamage;
import engine.game.Player;

import java.util.List;

public class Burn extends Effect {
    public Burn(int effectID, String name, int casterID, int targetID, int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        super(effectID, name, casterID, targetID, value, duration, abilityID, stackable, isVisible);
    }
    static int effectID = 1;
    static String name = "Burn";


    public static void add(int casterID, int targetID, int amount, int duration, int abilityID, boolean stackable, List<Player> playerList, List<Effect> effectList){
        if(!stackable){
            for(int i = 0;i<effectList.size();i++) {
                if(effectList.get(i).getCasterID() == casterID){
                    if(effectList.get(i).getEffectID() == effectID) {
                        effectList.remove(i);
                    }
                }
            }
        }
        effectList.add(new Effect(effectID,name,casterID,targetID,amount,duration,abilityID,stackable,true));

        System.out.println(playerList.get(targetID).getName() + " just started burning from " + playerList.get(casterID).getName()
                + "'s attacks for "+ amount + " damage, for the next " + duration + " rounds");
    }

    public static void damage(int casterID, int targetID, int value, List<Player> playerList){

        System.out.println(playerList.get(targetID).getName() + " is still burning from your attacks.");
        DealDamage.call(casterID, targetID, value, playerList);

    }
}
