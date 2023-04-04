package assets;

import assets.effects.Shield;
import assets.effects.ThornShield;
import engine.game.Player;

import java.util.ArrayList;
import java.util.List;

import static engine.game.Player.playerList;

public class Effect {
    private int effectID;
    private String name;
    private int casterID;
    private int targetID;
    private int value;
    private int duration;
    private int abilityID;
    private boolean stackable;
    private boolean isVisible;
    public static List<Effect> effectList = new ArrayList<>();

    public Effect(int effectID, String name,int casterID, int targetID, int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        this.effectID = effectID;
        this.name = name;
        this.casterID = casterID;
        this.targetID = targetID;
        this.value = value;
        this.duration = duration;
        this.abilityID = abilityID;
        this.stackable = stackable;
        this.isVisible = isVisible;
    }

    public List<Effect> init(){
        return effectList;
    }

    public static List<Effect> update(int casterID){
        int activeEffects = 0;

        for(int i = 0 ; i < effectList.size();i++){
            activeEffects++;                                                        //count all the effects found
            if(effectList.get(i).getCasterID() == casterID) {                       //look for effects that the caster made
                effectList.get(i).setDuration(effectList.get(i).getDuration() - 1); //lower their duration
                if (effectList.get(i).getDuration() == 0) {                         //if an effect reaches 0
                    if(effectList.get(i).getEffectID() == 5){
                                                                                    //if the effect is casting, we dont remove it here.
                    }else{
                        effectList.remove(i);                                       //remove the effect
                        activeEffects--;                                            //and lower the counter
                    }
                }
            }
        }
        if(activeEffects > 0){                                                      //if we have more than 0 effects, show them
            for (Effect effect : effectList) {
                System.out.println(effect.toString());
            }
        }else{
            System.out.println("None.");                                            //if no effect are found, print none.
        }

        return effectList;
    }

    public static int checkPlayerEffects(int casterID, int targetID, int value, int actionID){

        if(actionID == 0) {
            for (int i = 0; i < effectList.size(); i++) {
                if(effectList.get(i).getCasterID() == targetID) {
                    if (effectList.get(i).getEffectID() == 4) {
                        value = ThornShield.activate(casterID, targetID, value, playerList);
                    }
                }
            }
            if(value>0){
                for (int i = 0; i < effectList.size(); i++) {
                    if (effectList.get(i).getEffectID() == 3) {
                        value = Shield.destroy(casterID, targetID, value, playerList);
                    }
                }
            }
        }

        return value;
    }

    public int getEffectID() {
        return effectID;
    }

    public void setEffectID(int effectID) {
        this.effectID = effectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isStackable() {
        return stackable;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAbilityID() {
        return abilityID;
    }

    public void setAbilityID(int abilityID) {
        this.abilityID = abilityID;
    }

    public static List<Effect> getEffectList() {
        return effectList;
    }

    public static void setEffectList(List<Effect> effectList) {
        Effect.effectList = effectList;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }



    @Override
    public String toString() {
        if(isVisible){
            return playerList.get(targetID).getName() + ": "+ name + ", value=" + value +", remaining rounds=" + duration + ", from: " + playerList.get(casterID).getName();
        }else{
            return "";
        }

    }

    public int getTargetID() {
        return targetID;
    }

    public void setTargetID(int targetID) {
        this.targetID = targetID;
    }

    public int getCasterID() {
        return casterID;
    }

    public void setCasterID(int casterID) {
        this.casterID = casterID;
    }
}
