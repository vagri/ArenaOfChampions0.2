package assets.abilities;

import assets.Ability;
import assets.Effect;
import assets.effects.Bleed;
import assets.effects.MageHellFire;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class Spellbook extends Ability {
    public Spellbook(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 5;


    static String name = "Passive: Spellbook: ";
    static String info = "Casting an ability 3 times total, upgrades it.";

    static boolean available = true;
    static boolean finished = false;

    public static void printinfo(){
        System.out.print(name);
        System.out.println(info);
    }
    public static void call(int casterID, int abilityID,int effectID,String effectname, List<Player> playerList, List<Effect> effectList) {

    Integer effect = null;

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
                boolean fireStormMastered = false;
                for (int i = 0; i < effectList.size(); i++) {
                    if (effectList.get(i).getCasterID() == casterID) {
                        if (effectList.get(i).getEffectID() == 9) {
                            fireStormMastered = true;
                        }
                    }
                }
                if(!fireStormMastered){
                    MageHellFire.add(casterID, 9, playerList, effectList);
                }
            }
        }
    }
}
