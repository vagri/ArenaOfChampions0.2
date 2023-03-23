package assets.champions;

import assets.Champion;
import engine.game.Player;

import java.util.List;

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



    public static void load(){
        championList.add(new Champion(championID,name,abilities,maxHP,maxR,resourceID,type));
    }

    public static void play(int casterID, List<Player> playerList) {

    }
}