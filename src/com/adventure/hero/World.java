package com.adventure.hero;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static final String SPACE = " ";

    private List<Character> characters = new ArrayList<>();
    private boolean casualty = false;

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public boolean hasCasualty() {
        return casualty;
    }

    public void kill(Character killer, Character killee) {
        this.characters.remove(killee);
        System.out.println(killer + " killed " + killee + "!");
        this.casualty = true;
    }

    public void simulateStep() {
        for (Character c : characters) {
            int decision = c.think();
            int decisionType = (decision >> 1) & 0x03;
            int position = characters.indexOf(c);

            switch(decisionType) {
                case 0: // Heal
                    c.heal();
                    printAction(c.name, "heals", "himself");
                    break;
                case 1: // Hit previous character
                    if (position == 0) {
                        c.hit(characters.get(characters.size() - 1));
                        printAction(c.name, "hits", characters.get(characters.size() - 1).name);
                    } else {
                        c.hit(characters.get(position - 1));
                        printAction(c.name, "hits", characters.get(position - 1).name);
                    }
                    break;
                case 2: // Hit next character
                    if (position == characters.size() - 1) {
                        c.hit(characters.get(0));
                        printAction(c.name, "hits", characters.get(0).name);
                    } else {
                        c.hit(characters.get(position + 1));
                        printAction(c.name, "hits", characters.get(position + 1).name);
                    }
                default:
                    throw new IllegalArgumentException("Invalid decisionType: " + decisionType);
            }
        }
    }

    private void printAction(String subject, String action, String object) {
        System.out.println(subject + SPACE + action + SPACE + object);
    }


    public void dumpState() {
        System.out.println("=== Game state:");
        for (Character c : characters) {
            System.out.println(c.name + ": life=" + c.getLife() + ", stamina=" + c.getStamina());
        }
    }
}
