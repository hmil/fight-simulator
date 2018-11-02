package com.adventure.hero.engine;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static final String SPACE = " ";

    private List<Player> players = new ArrayList<>();
    private boolean casualty = false;

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public boolean hasCasualty() {
        return casualty;
    }

    public void kill(Player killer, Player killee) {
        this.players.remove(killee);
        System.out.println(killer + " killed " + killee + "!");
        this.casualty = true;
    }

    public void simulateStep() {
        for (Player c : players) {
            int decision = c.think();
            int position = players.indexOf(c);

            switch(decision) {
                case 0: // Heal
                    c.heal();
                    printAction(c.name, "heals", "himself");
                    break;
                case 1: // Hit previous player
                    if (position == 0) {
                        c.hit(players.get(players.size() - 1));
                        printAction(c.name, "hits", players.get(players.size() - 1).name);
                    } else {
                        c.hit(players.get(position - 1));
                        printAction(c.name, "hits", players.get(position - 1).name);
                    }
                    break;
                case 2: // Hit next player
                    if (position == players.size() - 1) {
                        c.hit(players.get(0));
                        printAction(c.name, "hits", players.get(0).name);
                    } else {
                        c.hit(players.get(position + 1));
                        printAction(c.name, "hits", players.get(position + 1).name);
                    }
                default:
                    throw new IllegalArgumentException("Invalid decisionType: " + decision);
            }
        }
    }

    private void printAction(String subject, String action, String object) {
        System.out.println(subject + SPACE + action + SPACE + object);
    }


    public void dumpState() {
        System.out.println("=== Game state:");
        for (Player c : players) {
            System.out.println(c.name + ": life=" + c.getLife() + ", stamina=" + c.getStamina());
        }
    }
}
