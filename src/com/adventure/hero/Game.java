package com.adventure.hero;

public class Game {

    public static void main(String[] args) {
        World world = new World();

        world.addCharacter(new Character(world, "Neo", 80, 100, 20));
        world.addCharacter(new Character(world, "Agent Smith", 100, 100, 20));
        world.addCharacter(new Character(world, "The Merovingian", 60, 70, 25));

        // Run the game until one character dies
        while (!world.hasCasualty()) {
            world.dumpState();
            world.simulateStep();
        }

        System.out.println("Game over!");
    }
}
