package assets.actions;

import assets.Action;
import assets.effects.Injured;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class HealHealth extends Action{
    public HealHealth(int actionID, String name) {
        super(actionID, name);
    }

    static int actionID = 3;

    public static void call(int casterID, int targetID, int value, List<Player> playerList){

        playerList.get(targetID).setCurrentHP(playerList.get(targetID).getCurrentHP() + value);
        if(playerList.get(targetID).getCurrentHP() > playerList.get(targetID).getMaxHP()){
            playerList.get(targetID).setCurrentHP(playerList.get(targetID).getMaxHP());
        }

        if(casterID == targetID){
            System.out.println(playerList.get(casterID).getName() + " healed for " + value + " Health");
        }else{
            System.out.println(playerList.get(casterID).getName() + " healed " + playerList.get(targetID).getName() + " for " +
                    value + " Health.");
        }

        Injured.update(targetID, targetID, playerList.get(targetID).getCurrentHP(), -1, -1, false, playerList, effectList);
            }
}
