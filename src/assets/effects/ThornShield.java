package assets.effects;

import assets.Effect;
import assets.actions.DealDamage;
import engine.game.Player;

import java.util.List;

public class ThornShield extends Effect {

    public ThornShield(int effectID, String name, int casterID, int targetID, int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        super(effectID, name, casterID, targetID, value, duration, abilityID, stackable, isVisible);
    }

    static int effectID = 4;
    static String name = "Thornshield";

    public static void add(int casterID, int targetID, int amount, int duration, int abilityID, boolean stackable, List<Player> playerList, List<Effect> effectList){
        if(!stackable){
            for(int i = 0;i<effectList.size();i++) {
                if(effectList.get(i).getAbilityID() == abilityID) {
                    effectList.remove(i);
                }
            }
        }
        effectList.add(new Effect(effectID,name,casterID,targetID,amount,duration,abilityID,stackable,true));

        if(playerList.get(casterID).getID() == playerList.get(targetID).getID()) {
            System.out.println(playerList.get(casterID).getName() + " applied Thorns equal to " + amount
                    + " damage to themselves for " + duration + " rounds");
        }else{
            System.out.println(playerList.get(casterID).getName() + " applied Thorns equal to " + amount
                    + " damage to " + playerList.get(targetID).getName() + " for " + duration + " rounds");
        }
    }

    public static int activate(int casterID, int targetID, int value, List<Player> playerList){
        int minDuration = 10;
        int minPosition = -1;
        boolean foundShields = false;

        do {               //thornshield.activate works the same way as shield.destroy, with the difference that here the caster takes damage
            for (int i = 0; i < effectList.size(); i++) {
                if(effectList.get(i).getCasterID() == targetID) {
                    if (effectList.get(i).getEffectID() == 4) {
                        if (minDuration > effectList.get(i).getDuration()) {
                            minDuration = effectList.get(i).getDuration();
                            minPosition = i;
                        }
                        foundShields = true;
                    }
                }
            }
            if(!foundShields) {
                return value;
            }
            if(effectList.get(minPosition).getValue() > value){
                System.out.println("A Thornshield has been activated by " + playerList.get(casterID).getName() +
                        " while attacking " + playerList.get(targetID).getName());

                DealDamage.call(targetID,casterID,value,playerList);

                effectList.get(minPosition).setValue(effectList.get(minPosition).getValue() - value);

                value = 0;

            }else if(effectList.get(minPosition).getValue() == value){
                System.out.println("A Thornshield has been activated by " + playerList.get(casterID).getName() +
                        " while attacking " + playerList.get(targetID).getName());

                effectList.remove(minPosition);

                DealDamage.call(targetID, casterID , value,  playerList);

                System.out.println("The Thornshield has been broken.");

                value = 0;

            }else{
                System.out.println("A Thornshield has been activated by " + playerList.get(casterID).getName() +
                        " while attacking " + playerList.get(targetID).getName());

                DealDamage.call(targetID, casterID , effectList.get(minPosition).getValue(),  playerList);

                value = value - effectList.get(minPosition).getValue();

                effectList.remove(minPosition);

                System.out.println("The Thornshield has been broken.");
            }
            foundShields = false;
        }while(value !=0);

        return value;
    }

}
