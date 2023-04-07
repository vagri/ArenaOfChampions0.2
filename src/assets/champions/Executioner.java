package assets.champions;

import assets.Champion;
import assets.abilities.*;
import engine.game.Player;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Executioner extends Champion {

    public Executioner(int championID, String name, int[] abilities, int maxHP, int maxR, int resource, int type) {
        super(championID, name, abilities, maxHP, maxR, resource, type);
    }

    static int championID = 0;
    static String name = "Executioner";
    static int[] abilities = {0,1,2,3,4};
    static int maxHP = 360;
    static int maxR = 100;
    static int resourceID = 1;
    static int type = 0;
    static boolean[] availability = {false,true,true,true,true};
    static boolean finished = false;

    public static void load(){
        championList.add(new Champion(championID,name,abilities,maxHP,maxR,resourceID,type));
    }

    public static void play(int casterID, List<Player> playerList){

        GrieviousWounds.check(casterID, playerList);//checking if the executioner is bleeding someone

        System.out.println("Choose ability to cast:");
        do{
            GrieviousWounds.printinfo();
            availability[1] = MortalStrike.checkAvailability(casterID,playerList);
            availability[2] = Bloodthirster.checkAvailability(casterID,playerList);
            availability[3] = CripplingAttack.checkAvailability(casterID,playerList);
            availability[4] = Execution.checkAvailability(casterID,playerList);
            System.out.println("5) Do Nothing");

            Scanner reader = new Scanner(System.in);
            int choise = reader.nextInt();

            switch(choise){
                case 1:
                    if(availability[1]){
                        finished = MortalStrike.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 2:
                    if(availability[2]){
                        finished = Bloodthirster.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 3:
                    if(availability[3]){
                        finished = CripplingAttack.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 4:
                    if(availability[4]){
                        finished = Execution.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 5:
                    System.out.println("You do not do anything.");
                    finished = true;
                    break;
                default:
                    System.out.println("This is not an ability.");
            }
            if(!finished){
                System.out.println("Choose another ability.");
            }
        }while(finished == false);

    }
}
