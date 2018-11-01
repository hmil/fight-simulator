package com.adventure.hero.engine;

public class Player {

    /**
     * The players life. From 0 to 100
     */
    private int life;

    /**
     * The players energy. From 0 to 100
     */
    private int stamina;

    /**
     * How much damage per attack this player makes
     */
    private final int damage;

    /**
     * Name of this player
     */
    public final String name;


    private final World world;

    public Player(
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

    /**
     * This player attempts to hits another player.
     * @param other player that gets hit
     */
    public void hit(Player other) {
        if (this.stamina > 20) {
            this.stamina -= 20;
            other.life -= damage;
            if (other.life < 0) {
                world.kill(this, other);
            }
        }
    }

    /**
     * Decides what to do this turn.
     *
     * @return The decision, encoded in the two least significant bits of an int, as follows:
     *    00 = heal self
     *    01 = hit next player
     *    10 = hit previous player
     */
    public int think() {
        // Because the decision takes 2 bits, we generate a random number from 0 (binary 00) to 3 (binary 11)
        return (int) Math.random() * 3;
    }

    public int getLife() {
        return life;
    }

    public int getStamina() {
        return stamina;
    }
}