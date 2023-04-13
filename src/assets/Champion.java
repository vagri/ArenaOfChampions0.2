package assets;

import assets.champions.Executioner;
import assets.champions.Mage;
import assets.champions.Priest;
import engine.game.Player;

import java.util.ArrayList;
import java.util.List;


public class Champion {

    private int championID;
    private String name;
    private static int[] abilities;
    private int maxHP;
    private int maxR;
    private int resource;
    private int type;

    public Champion(int championID, String name, int[] abilities, int maxHP, int maxR, int resource, int type) {
        this.championID = championID;
        this.name = name;
        this.abilities = abilities;
        this.maxHP = maxHP;
        this.maxR = maxR;
        this.resource = resource;
        this.type = type;
    }

    public static List<Champion> championList = new ArrayList<>();

    public static List<Champion> loadStats(){
        Executioner.load();
        Mage.load();
        Priest.load();
        return championList;
    }

    public int getChampionID() {
        return championID;
    }

    public void setChampionID(int championID) {
        this.championID = championID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getAbilities() {
        return abilities;
    }

    public void setAbilities(int[] abilities) {
        this.abilities = abilities;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxR() {
        return maxR;
    }

    public void setMaxR(int maxR) {
        this.maxR = maxR;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
