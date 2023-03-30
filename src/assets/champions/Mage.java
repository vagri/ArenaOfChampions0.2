package assets.champions;

import assets.Champion;
import assets.abilities.ManaShield;
import assets.abilities.MortalStrike;
import engine.game.Player;

import java.util.List;
import java.util.Scanner;

public class Mage extends Champion {

    public Mage(int championID, String name, int[] abilities, int maxHP, int maxR, int resource, int type) {
        super(championID, name, abilities, maxHP, maxR, resource, type);
    }

    static int championID = 1;
    static String name = "Mage";
    static int[] abilities = {5,6,7,8,9,};// 10,11,12,13 are the mage's alternate abilities
    static int maxHP = 240;
    static int maxR = 400;
    static int resourceID = 0;
    static int type = 0;

    static boolean[] availability = {true,true,true,true};
    static boolean finished = false;


    public static void load(){
        championList.add(new Champion(championID,name,abilities,maxHP,maxR,resourceID,type));
    }

    public static void play(int attackerID, List<Player> playerList){
        System.out.println("Choose ability to cast:");

        availability[3] = ManaShield.checkAvailability(attackerID,playerList);

        System.out.println("5) Do Nothing");

        Scanner reader = new Scanner(System.in);
        int choise = reader.nextInt();

        do{
            switch(choise){
                case 1:

                    break;
                case 2:


                    break;
                case 3:
                    if(availability[3]){
                        finished = ManaShield.cast(attackerID,playerList);
                    }else{
                        System.out.println("This ability cannot be cast!");
                    }
                    break;
                case 4:

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
        }while(finished = false);

    }
}