package assets.actions;

import assets.Action;
import assets.Effect;
import engine.game.Player;

import java.util.List;

public class RestoreResource extends Action {
    public RestoreResource(int effectID, String name) {
        super(effectID, name);
    }

    static int actionID = 1;

    public static void call(int casterID, int targetID, int value, List<Player> playerList){

        playerList.get(targetID).setCurrentR(playerList.get(targetID).getCurrentR() + value);
        if(playerList.get(targetID).getCurrentR() > playerList.get(targetID).getMaxR()){
            playerList.get(targetID).setCurrentR(playerList.get(targetID).getMaxR());
        }

        if(casterID == targetID){
            System.out.println(playerList.get(casterID).getName() + " gained " + value + " " + playerList.get(targetID).getResource());
        }else{
            System.out.println(playerList.get(casterID).getName() + " granted " + playerList.get(targetID).getName() +", " + value +
                    " " + playerList.get(targetID).getResource());
        }
    }
}
