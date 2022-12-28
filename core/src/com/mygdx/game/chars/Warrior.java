package com.mygdx.game.chars;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public abstract class Warrior extends NPC {
    public Warrior(int attack, int protection, int[] damage, double health, int speed, States state) {
        super(attack, protection, damage, health, speed, state);
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

        PrintStream var10000 = System.out;
        String var10001 = this.getClass().getSimpleName();
        var10000.println(var10001 + "->" + ((NPC)team.get(index)).getClass().getSimpleName() + ((NPC)team.get(index)).getHealth());
        return (NPC)team.get(index);
    }

    public void step(List<NPC> team) {
        if (!this.getState().equals(States.DEAD)) {
            NPC target = this.findTarget(team);
            if (this.getPosition().getDistance(target.getPosition()) <= 1.0F) {
                this.getAttack(target);
            } else {
                this.move(team, target);
                this.setState(States.WALK);
            }

        }
    }

    private void move(List<NPC> team, NPC target) {
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        if (target.getDestination().y > y && this.checkPosition(new Vector2(x, y++))) {
            this.setDestination(new Vector2(x, y++));
        }

        if (target.getDestination().y < y && this.checkPosition(new Vector2(x, y--))) {
            this.setDestination(new Vector2(x, y--));
        }

        if (target.getDestination().x > x && this.checkPosition(new Vector2(x++, y))) {
            this.setDestination(new Vector2(x++, y));
        }

        if (target.getDestination().x < x && this.checkPosition(new Vector2(x--, y))) {
            this.setDestination(new Vector2(x--, y));
        }

    }

    private boolean checkPosition(Vector2 pos) {
        Iterator var2 = this.getMyTeam().iterator();

        NPC npc;
        do {
            if (!var2.hasNext()) {
                return true;
            }

            npc = (NPC)var2.next();
        } while(!pos.isEqual(npc.getPosition()) || npc.getState() == States.DEAD);

        return false;
    }
}
