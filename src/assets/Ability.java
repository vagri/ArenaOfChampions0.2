package assets;

import assets.champions.Executioner;
import assets.champions.Mage;
import engine.game.Player;

import java.util.List;
import java.util.Scanner;

public class Ability {
    private int abilityID;
    private String name;
    private int value;
    private int cost;
    private int CD;
    private int isCastable;
    private int isPassive;

    public Ability(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        this.abilityID = abilityID;
        this.name = name;
        this.value = value;
        this.cost = cost;
        this.CD = CD;
        this.isCastable = isCastable;
        this.isPassive = isPassive;
    }

    public static void choose(int casterID,List<Player> playerList){
        int championID = playerList.get(casterID).getChampionID();
        switch (championID){
            case 0:
                Executioner.play(casterID,playerList);
                break;
            case 1:
                Mage.play(casterID,playerList);
                break;
        }
    }

    public static int pickTarget(int casterID, List<Player> playerList, boolean targetsEnemies, boolean targetsAllies, boolean targetself) {

        System.out.println("Pick a target:");
        for(Player player : playerList){
            System.out.println(player.toString());
        }
        System.out.println("Press 0 to choose another ability.");

        int targetID,realID;
        boolean targetfound = false;

        do {
            Scanner reader = new Scanner(System.in);
            targetID = reader.nextInt();

            if(targetID == 0){
                targetID = -2;
            }else {
                for (int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getID() == targetID) {
                        targetfound = true;
                        targetID = i;
                        System.out.println("targetid is found and is " + targetID);
                    }
                }
                if (!targetfound) {
                    System.out.println("This player does not exist");
                    targetID = -1;
                    System.out.println("Pick another target!");
                } else if (playerList.get(casterID).getID() == playerList.get(targetID).getID() && !targetself) {
                    System.out.println("You cannot target yourself!");
                    targetID = -1;
                    System.out.println("Pick another target!");
                } else if (playerList.get(casterID).getTeamID() == playerList.get(targetID).getTeamID() && playerList.get(casterID).getID() != playerList.get(targetID).getID() && !targetsAllies) {
                    System.out.println("You cannot target an ally!");
                    targetID = -1;
                    System.out.println("Pick another target!");
                } else if (playerList.get(casterID).getTeamID() != playerList.get(targetID).getTeamID() && !targetsEnemies) {
                    System.out.println("You cannot target an enemy!");
                    targetID = -1;
                    System.out.println("Pick another target!");
                } else if (playerList.get(targetID).isDead()) {
                    System.out.println("This target is dead!");
                    targetID = -1;
                    System.out.println("Pick another target!");
                }
            }
        }while(targetID == -1);

        return targetID;
    }

    public int getAbilityID() {
        return abilityID;
    }

    public void setAbilityID(int abilityID) {
        this.abilityID = abilityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCD() {
        return CD;
    }

    public void setCD(int CD) {
        this.CD = CD;
    }

    public int getIsCastable() {
        return isCastable;
    }

    public void setIsCastable(int isCastable) {
        this.isCastable = isCastable;
    }

    public int getIsPassive() {
        return isPassive;
    }

    public void setIsPassive(int isPassive) {
        this.isPassive = isPassive;
    }
}
