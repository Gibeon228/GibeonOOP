package com.mygdx.game.chars;

import java.util.Arrays;
import java.util.List;

import java.util.List;

public abstract class NPC implements NPCInterface {
    private int attack;
    private int protection;
    private int[] damage;
    private double maxHealth;
    private double health;
    private int speed;
    private States state;
    protected List<NPC> myTeam;
    protected Vector2 position;
    Vector2 destination;
    public boolean fire;

    public NPC(int attack, int protection, int[] damage, double health, int speed, States state) {
        this.attack = attack;
        this.protection = protection;
        this.damage = damage;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.state = state;
    }

    public Vector2 getDestination() {
        if (this.destination == null) {
            this.destination = this.position;
        }

        return this.destination;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public List<NPC> getMyTeam() {
        return this.myTeam;
    }

    public States getState() {
        return this.state;
    }

    public int[] getDamage() {
        return this.damage;
    }

    public double getHealth() {
        return this.health;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getProtection() {
        return this.protection;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setState(States state) {
        this.state = state;
        System.out.println(state);
    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setHealth(double health) {
        if (health > this.getMaxHealth()) {
            this.health = this.maxHealth;
        } else {
            this.health = health;
        }

    }

    public String getInfo() {
        String str = "" + this.state;
        String str2 = "" + (int)this.health;
        String var10000 = this.getClass().getSimpleName();
        return var10000 + str2 + "/" + (int)this.maxHealth + " " + str;
    }

    protected void getAttack(NPC h) {
        this.setState(States.ATTACK);
        h.setState(States.HURT);
        if (this.attack == h.protection) {
            if ((float)this.speed < this.position.getDistance(h.getPosition())) {
                h.health -= (double)((this.damage[0] + this.damage[1]) / 4);
            } else {
                h.health -= (double)((this.damage[0] + this.damage[1]) / 2);
            }
        }

        if (this.attack > h.protection) {
            if ((float)this.speed < this.position.getDistance(h.getPosition())) {
                h.health -= (double)(this.damage[1] / 2);
            } else {
                h.health -= (double)this.damage[1];
            }
        }

        if (this.attack < h.protection) {
            if ((float)this.speed < this.position.getDistance(h.getPosition())) {
                h.health -= (double)(this.damage[0] / 2);
            } else {
                h.health -= (double)this.damage[0];
            }
        }

        if (h.health <= 0.0) {
            h.health = 0.0;
            h.setState(States.DEAD);
        }

    }

    public void step(List<NPC> team) {
    }
}