package assets.actions;

import assets.Action;
import engine.game.Player;

import java.util.List;

public class DrainResource extends Action {
    public DrainResource(int actionID, String name) {
        super(actionID, name);
    }

    static int actionID = 2;

    public static void call(int casterID, int targetID, int value, List<Player> playerList) {

        playerList.get(targetID).setCurrentR(playerList.get(targetID).getCurrentR() - value);
        if (playerList.get(targetID).getCurrentR() < 0) {
            playerList.get(targetID).setCurrentR(0);
        }

        if(casterID == targetID){
            System.out.println(playerList.get(casterID).getName() + " lost " + value + " " + playerList.get(targetID).getResource());
        }else{
            System.out.println(playerList.get(casterID).getName() + " drained from " + playerList.get(targetID).getName() +", " + value +
                    " " + playerList.get(targetID).getResource());
        }

    }
}
