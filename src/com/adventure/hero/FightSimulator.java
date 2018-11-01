package com.adventure.hero;

import com.adventure.hero.engine.World;
import com.adventure.hero.engine.Player;

public class FightSimulator {

    public static void main(String[] args) {
        World world = new World();

        world.addPlayer(new Player(world, "Neo", 80, 100, 20));
        world.addPlayer(new Player(world, "Agent Smith", 100, 100, 20));
        world.addPlayer(new Player(world, "The Merovingian", 60, 70, 25));

        // Run the game until one player dies
        while (!world.hasCasualty()) {
            world.dumpState();
            System.out.println("\n=== Simulating next step ===");
            world.simulateStep();
        }

        System.out.println("Game over!");
    }
}
