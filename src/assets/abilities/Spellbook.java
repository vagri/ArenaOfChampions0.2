package assets.abilities;

import assets.Ability;
import assets.effects.Bleed;
import engine.game.Player;

import java.util.List;

import static assets.Effect.effectList;

public class Spellbook extends Ability {
    public Spellbook(int abilityID, String name, int value, int cost, int CD, int isCastable, int isPassive) {
        super(abilityID, name, value, cost, CD, isCastable, isPassive);
    }
    static int abilityID = 5;


    static String name = "Passive: Spellbook: ";
    static String info = "Casting an ability 3 times total, upgrades it.";

    static boolean available = true;
    static boolean finished = false;

    public static void printinfo(){
        System.out.print(name);
        System.out.println(info);
    }
}
