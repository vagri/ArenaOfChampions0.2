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
    private int playerID;
    private int value;
    private int duration;
    private int abilityID;
    private boolean stackable;
    private boolean isVisible;
    public static List<Effect> effectList = new ArrayList<>();

    public Effect(int effectID, String name,int playerID, int value, int duration, int abilityID, boolean stackable, boolean isVisible) {
        this.effectID = effectID;
        this.name = name;
        this.playerID = playerID;
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
        for(int i = 0 ; i < effectList.size();i++){
            if(effectList.get(i).getPlayerID() == casterID) {
                effectList.get(i).setDuration(effectList.get(i).getDuration() - 1);
                if (effectList.get(i).getDuration() == 0) {
                    effectList.remove(i);
                }
            }

        }

        for (Effect effect : effectList) {
            System.out.println("effects");
            System.out.println(effect.toString());
        }

        return effectList;
    }

    public static int checkPlayerEffects(int casterID, int targetID, int value, int actionID){

        if(actionID == 0) {
            for (int i = 0; i < effectList.size(); i++) {
                if(effectList.get(i).getPlayerID() == targetID) {
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

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    @Override
    public String toString() {
        return "Effect{" +
                "effectID=" + effectID +
                ", name='" + name + '\'' +
                ", playerID=" + playerID +
                ", value=" + value +
                ", duration=" + duration +
                ", stackable=" + stackable +
                ", isVisible=" + isVisible +
                '}';
    }
}
