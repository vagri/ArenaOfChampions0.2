package assets.champions;

import assets.Champion;
import assets.abilities.*;
import engine.game.Player;

import java.util.List;
import java.util.Scanner;

public class Priest extends Champion {
    public Priest(int championID, String name, int[] abilities, int maxHP, int maxR, int resource, int type) {
        super(championID, name, abilities, maxHP, maxR, resource, type);
    }
    static int championID = 3;
    static String name = "Priest";
    static int[] abilities = {10,11,12,13,14};
    static int maxHP = 220;
    static int maxR = 370;
    static int resourceID = 0;
    static int type = 0;
    static boolean[] availability = {false,true,true,true,true};
    static boolean finished = false;

    public static void load(){
        championList.add(new Champion(championID,name,abilities,maxHP,maxR,resourceID,type));
    }

    public static void play(int casterID,String state, List<Player> playerList){

        System.out.println("Choose ability to cast:");
        finished = false;
        do{
            availability[1] = Smite.checkAvailability(casterID,playerList);
            availability[2] = HolyShield.checkAvailability(casterID,playerList);
            availability[3] = FlashHeal.checkAvailability(casterID,playerList);
            availability[4] = GrandWish.checkAvailability(casterID,playerList);
            System.out.println("5) Do Nothing");

            Scanner reader = new Scanner(System.in);
            int choise = reader.nextInt();

            switch(choise){
                case 1:
                    if(availability[1]){
                        finished = Smite.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 2:
                    if(availability[2]){
                        finished = HolyShield.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 3:
                    if(availability[3]){
                        finished = FlashHeal.cast(casterID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 4:
                    if(availability[4]){
                        finished = GrandWish.cast(casterID,playerList);
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
