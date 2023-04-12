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

    public static void choose(int casterID, String state, List<Player> playerList){
        int championID = playerList.get(casterID).getChampionID();
        switch (championID){
            case 0:
                Executioner.play(casterID,playerList);
                break;
            case 1:
                Mage.play(casterID,state,playerList);
                break;
        }
    }

    public static int pickTarget(int casterID, List<Player> playerList, boolean targetsEnemies, boolean targetsAllies, boolean targetself) {
        int team1Size=0;
        int team2Size=0;
        for(int i = 0;i<playerList.size();i++){//counting how many players are remaining from each team
            if(playerList.get(i).getTeamID() == 1){
                team1Size++;
            }else{
                team2Size++;
            }
        }

        int targetID = -2;

        targetID = instantPick(casterID,team1Size, team2Size, playerList, targetsEnemies, targetsAllies ,targetself);

        if (targetID == -2){//if the target is not instant, ask the user

            System.out.println("Pick a target:");
            for(Player player : playerList){
                System.out.println(player.toString());
            }
            long timeInMilliSeconds = 1000;
            long timestamp = System.currentTimeMillis();

            do {

            } while (System.currentTimeMillis() < timestamp + timeInMilliSeconds);
            System.out.println("Press 0 to choose another ability.");


            boolean targetfound = false;
            int realID;
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
        }



        return targetID;
    }

    public static int instantPick(int casterID,int team1size,int team2size, List<Player> playerList, boolean targetsEnemies, boolean targetsAllies, boolean targetself) {

        
        if(targetsAllies == false && targetsEnemies == false && targetself==true){// check if this its self cast ONLY
            return casterID;// this may break
        }else if(playerList.get(casterID).getTeamID()==1){// if not check the team to get the correct measures, this is for team 1 player
            if((targetsEnemies == true && targetsAllies == false && targetself == false)&&(team2size == 1) ){// if there is only one enemy and you can only target enemies
                for (int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getTeamID() == 2) {// find the enemy
                        return i;
                    }
                }
            }else if((targetsEnemies == false && targetsAllies == true && targetself == true)&&(team1size == 1) ){//if there are no allies and you can target yourself
                return casterID;
            }else if((targetsEnemies == false && targetsAllies == true && targetself == false)&&(team1size == 2) ){//if there is one ally and you cannot target yourself
                for (int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getTeamID() == 1) {// find the ally
                        return i;
                    }
                }
            }
        }else{// now do it again for team 2, with inverted team values
            if((targetsEnemies == true && targetsAllies == false && targetself == false)&&(team1size == 1) ){// if there is only one enemy and you can only target enemies
                for (int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getTeamID() == 1) {// find the enemy
                        return i;
                    }
                }
            }else if((targetsEnemies == false && targetsAllies == true && targetself == true)&&(team2size == 1) ){//if there are no allies and you can target yourself
                return casterID;
            }else if((targetsEnemies == false && targetsAllies == true && targetself == false)&&(team2size == 2) ){//if there is one ally and you cannot target yourself
                for (int i = 0; i < playerList.size(); i++) {
                    if (playerList.get(i).getTeamID() == 2) {// find the ally
                        return i;
                    }
                }
            }
        }
        return casterID;
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
