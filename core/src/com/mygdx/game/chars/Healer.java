package com.mygdx.game.chars;

import java.util.List;

public abstract class Healer extends NPC {
    public Healer(int attack, int protection, int[] damage, double health, int speed, States state) {
        super(attack, protection, damage, health, speed, state);
    }

    public String getInfo() {
        return super.getInfo();
    }

    public void step(List<NPC> team) {
        if (this.getState() != States.DEAD) {
            this.setState(States.ATTACK);
            int index = this.findMinHealth();
            NPC teamMateNeedHealth = (NPC)this.getMyTeam().get(index);
            teamMateNeedHealth.setHealth(teamMateNeedHealth.getHealth() - (double)this.getDamage()[0]);
        }
    }

    private int findMinHealth() {
        double minHealth = Double.MAX_VALUE;
        int index = 0;

        for(int i = 0; i < this.getMyTeam().size(); ++i) {
            if (((NPC)this.getMyTeam().get(i)).getHealth() / ((NPC)this.getMyTeam().get(i)).getMaxHealth() < minHealth && ((NPC)this.getMyTeam().get(i)).getState() != States.DEAD) {
                minHealth = ((NPC)this.getMyTeam().get(i)).getHealth() / ((NPC)this.getMyTeam().get(i)).getMaxHealth();
                index = i;
            }
        }

        return index;
    }
}