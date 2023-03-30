package assets.effects;

import assets.Effect;
import assets.actions.DealDamage;
import engine.game.Player;

import java.util.List;

public class Shield extends Effect {

    public Shield(int effectID, String name, int playerID,int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        super(effectID, name, playerID,value, duration,abilityID, stackable, isVisible);
    }

    static int effectID = 3;
    static String name = "Shield";


    public static void add(int casterID, int targetID, int amount, int duration,int abilityID, boolean stackable, List<Player> playerList, List<Effect> effectList){
        if(!stackable){
            for(int i = 0;i<effectList.size();i++) {
                if(effectList.get(targetID).getAbilityID() == effectList.get(i).getAbilityID()) {
                    effectList.remove(i);
                }
            }
        }
        effectList.add(new Effect(effectID,name,targetID,amount,duration,abilityID,stackable,true));

        if(playerList.get(casterID).getID() == playerList.get(targetID).getID()) {
            System.out.println(playerList.get(casterID).getName() + " applied a Shield of " + amount
                    + " to themselves for " + duration + " rounds");
        }else{
            System.out.println(playerList.get(casterID).getName() + " applied a Shield of " + amount
                    + " to " + playerList.get(targetID).getName() + " for " + duration + " rounds");
        }
    }

    public static int destroy(int casterID, int targetID, int value, List<Player> playerList){
        int minDuration = 10;
        int minPosition = -1;
        boolean foundShields = false;

        do {               // run until the damage is absorbed or the shields are broken
            for (int i = 0; i < effectList.size(); i++) { //find lowest remaining time shield
                if(effectList.get(i).getPlayerID() == targetID) {
                    if (effectList.get(i).getEffectID() == 3) {
                        if (minDuration > effectList.get(i).getDuration()) {
                            minDuration = effectList.get(i).getDuration();
                            minPosition = i;
                        }
                        foundShields = true;// checks if there are any shield remaining
                    }
                }
            }
            if(!foundShields) {// there are no more shields(happens the second time)
                return value;
            }
            if(effectList.get(minPosition).getValue() > value){ //shield is more than enough
                System.out.println("A Shield protected " + playerList.get(targetID).getName() +
                        " by shielding " + value + " damage from " + playerList.get(casterID).getName());

                effectList.get(minPosition).setValue(effectList.get(minPosition).getValue() - value);

                value = 0;
            }else if(effectList.get(minPosition).getValue() == value){// shield is just enough
                System.out.println("A Shield barely managed to protect " + playerList.get(targetID).getName() +
                        " by shielding " + value + " damage from " + playerList.get(casterID).getName() +
                        ", while breaking in the process");

                value = 0;

                effectList.remove(minPosition);
            }else{  //shield was not enough
                System.out.println(playerList.get(casterID).getName() + " broke a shield of " +
                        playerList.get(targetID).getName() + " by dealing " + value + " damage to it.");

                value = value - effectList.get(minPosition).getValue();

                effectList.remove(minPosition);
            }
            foundShields = false;
        }while(value !=0);

        return value;
    }

}
