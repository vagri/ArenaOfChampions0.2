package engine.lobby;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Press 0 to start the game.");
        Scanner reader = new Scanner(System.in);
        int start = reader.nextInt();
        if(start == 0){
            System.out.println("Launching...");
            Startup.openLobby();
        }else{
            System.out.println("Closing...");
        }
    }
}
