package engine.lobby;

import engine.game.Game;

import java.util.Scanner;

public class Startup {

    static String name1;
    static String name2;
    static int classID1;
    static int classID2;
    public static void openLobby(){
        System.out.println("Welcome to Arena of Champions!");
        System.out.println("Please choose a name!");
        chooseName();
        System.out.println("Pick your Champion.");
        pickCharacter();
        System.out.println("Starting Game!");
        Game.startGame(name1, name2, classID1, classID2);
    }

    public static void chooseName(){
        System.out.println("Player 1 choose name:");
        Scanner reader = new Scanner(System.in);
        name1 = reader.nextLine();

        System.out.println("Player 2 choose name:");
        reader = new Scanner(System.in);
        name2 = reader.nextLine();

    }

    public static void pickCharacter(){
        System.out.println("0 for Executioner.");
        System.out.println("1 for mage.");

        do{
            System.out.println(name1 + " choose Champion:");
            Scanner reader = new Scanner(System.in);
            classID1 = reader.nextInt();
            if(classID1==0){
                System.out.println("You have chosen the Executioner");
            }else if(classID1==1){
                System.out.println("You have chosen the Mage");
            }else{
                System.out.println("You need to choose 0 or 1, not " + classID1);
            }
        }while(classID1<0 || classID1>1);


        do{
            System.out.println(name2 + " choose Champion:");
            Scanner reader = new Scanner(System.in);
            classID2 = reader.nextInt();
            if(classID2==0){
                System.out.println("You have chosen the Executioner");
            }else if(classID2==1){
                System.out.println("You have chosen the Mage");
            }else{
                System.out.println("You need to choose 0 or 1, not " + classID2);
            }
        }while(classID1<0 || classID1>1);
    }
}
