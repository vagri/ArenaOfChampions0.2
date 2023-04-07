package assets.champions;

import assets.Champion;
import assets.abilities.*;
import engine.game.Player;

import java.util.List;
import java.util.Scanner;

public class Mage extends Champion {

    public Mage(int championID, String name, int[] abilities, int maxHP, int maxR, int resource, int type) {
        super(championID, name, abilities, maxHP, maxR, resource, type);
    }

    static int championID = 1;
    static String name = "Mage";
    static int[] abilities = {5,6,7,8,9,};
    static int maxHP = 240;
    static int maxR = 400;
    static int resourceID = 0;
    static int type = 0;

    static boolean[] availability = {false,true,true,true,true};
    static boolean finished;


    public static void load(){
        championList.add(new Champion(championID,name,abilities,maxHP,maxR,resourceID,type));
    }

    public static void play(int casterID,String state, List<Player> playerList){
        FireStorm.checkBurn(casterID, playerList);
        if(state == "Casting"){
            FireStorm.finishCast(casterID, playerList);
        }

        System.out.println("Choose ability to cast:");
        finished = false;
        do{
            availability[1] = ArcaneShot.checkAvailability(casterID,playerList);
            availability[2] = MagicShard.checkAvailability(casterID,playerList);
            availability[3] = ManaShield.checkAvailability(casterID,playerList);
            availability[4] = FireStorm.checkAvailability(casterID,playerList);
            System.out.println("5) Do Nothing");

            Scanner reader = new Scanner(System.in);
            int choise = reader.nextInt();

            switch(choise){
                case 1:
                    if(availability[1]){
                        finished = ArcaneShot.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 2:
                    if(availability[2]){
                        finished = MagicShard.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 3:
                    if(availability[3]){
                        finished = ManaShield.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 4:
                    if(availability[4]){
                        finished = FireStorm.cast(casterID,playerList);
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