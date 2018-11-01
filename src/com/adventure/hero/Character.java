package com.adventure.hero;

public class Character {

    /**
     * The characters life. From 0 to 100
     */
    private int life;

    /**
     * The characters energy. From 0 to 100
     */
    private int stamina;

    /**
     * How much damage per attack this character makes
     */
    private final int damage;

    /**
     * Name of this character
     */
    public final String name;


    private final World world;

    Character(
            World world,
            String name,
            int initialLife,
            int initialStamina,
            int damage) {
        this.life = initialLife;
        this.stamina = initialStamina;
        this.damage = damage;
        this.name = name;
        this.world = world;
    }

    public void heal() {
        this.life += 20;
        this.stamina += 40;
    }

    public void hit(Character other) {
        if (this.stamina > 20) {
            this.stamina -= 20;
            other.life -= damage;
            if (other.life < 0) {
                world.kill(this, other);
            }
        }
    }

    /**
     * Decides what to do this turn. Possible return value:
     *
     * lowest significant bit = target
     *    0 = target is next player
     *    1 = target is previous player
     * bits 1 and 2 = action type
     *    00 = heal self
     *    01 = hit next character
     *    10 = hit previous character
     */
    public int think() {
        // Because the decision takes 3 bits, we generate a random number from 0 to 7
        return (int) Math.random() * 7;
    }

    public int getLife() {
        return life;
    }

    public int getStamina() {
        return stamina;
    }
}