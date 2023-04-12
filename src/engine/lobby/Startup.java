package engine.lobby;

import engine.game.Game;

import java.util.Scanner;

public class Startup {

    public static void openLobby(){
        System.out.println("Welcome to Arena of Champions!");
        int mode = chooseMode();

        Game.startGame(mode);
    }

    public static int chooseMode(){
        boolean start = false;
        String mode;
        do{
            System.out.println("Choose a Game Mode:");
            System.out.println("Press 1 for 1v1.");
            System.out.println("Press 2 for 2v2.");
            System.out.println("Press 3 for 3v3.");
            Scanner reader = new Scanner(System.in);
            mode = reader.nextLine();
            switch (mode) {
                case "1":
                    System.out.println("1v1 chosen.");
                    return 1;
                case "2":
                    System.out.println("2v2 chosen.");
                    return 2;
                case "3":
                    System.out.println("3v3 chosen.");
                    return 3;
                default:
                    System.out.println("This is not a gamemode, please try again.");
            }
        }while(!start);
        return 0;
    }
}
