package assets.effects;

import assets.Effect;
import assets.actions.DealDamage;
import assets.actions.HealHealth;
import engine.game.Player;

import java.util.List;

public class Blessed extends Effect {
    public Blessed(int effectID, String name, int casterID, int targetID, int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        super(effectID, name, casterID, targetID, value, duration, abilityID, stackable, isVisible);
    }
    static int effectID = 10;
    static String name = "Blessed";

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



        if(playerList.get(casterID).getID() == playerList.get(targetID).getID()) {
            System.out.println(playerList.get(casterID).getName() + " just blessed themselves for " + duration + " rounds");
        }else{
            System.out.println(playerList.get(targetID).getName() + " just blessed " + playerList.get(casterID).getName()
                    +" ,for the next " + duration + " rounds");
        }
    }

    public static void heal(int casterID, int targetID, int value, List<Player> playerList){

        System.out.println(playerList.get(targetID).getName() + " is still blessed by you.");
        HealHealth.call(casterID, targetID, value, playerList);

    }
}
