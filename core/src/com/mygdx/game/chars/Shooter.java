package com.mygdx.game.chars;

import java.util.List;

public abstract class Shooter extends NPC {
    int shoots;

    public Shooter(int attack, int protection, int[] damage, double health, int speed, States state, int shoots) {
        super(attack, protection, damage, health, speed, state);
        this.shoots = shoots;
    }

    public void step(List<NPC> team) {
        if (this.getState() != States.DEAD) {
            for(int i = 0; i < this.getMyTeam().size(); ++i) {
                if (((NPC)this.getMyTeam().get(i)).getClass().getSimpleName().equals("Peasant") && ((NPC)this.getMyTeam().get(i)).getState().equals(States.ALIVE)) {
                    ++this.shoots;
                    ((NPC)this.getMyTeam().get(i)).setState(States.USED);
                    break;
                }
            }

            if (this.shoots > 0) {
                --this.shoots;
                NPC target = this.findTarget(team);
                this.getAttack(target);
            }
        }
    }

    private NPC findTarget(List<NPC> team) {
        float minDistance = Float.MAX_VALUE;
        int index = 0;

        for(int i = 0; i < team.size(); ++i) {
            if (((NPC)team.get(i)).getState() != States.DEAD && minDistance > this.getPosition().getDistance(((NPC)team.get(i)).getPosition())) {
                minDistance = this.getPosition().getDistance(((NPC)team.get(i)).getPosition());
                index = i;
            }
        }

        return (NPC)team.get(index);
    }
}