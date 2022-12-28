package com.mygdx.game.chars;

import java.util.List;

public class Peasant extends NPC {
    public Peasant(List<NPC> team, int x, int y) {
        super(1, 1, new int[]{1, 1}, 1.0, 3, States.ALIVE);
        super.myTeam = team;
        super.position = new Vector2(x, y);
    }

    public void step(List<NPC> team) {
        if (this.getState().equals(States.USED)) {
            this.setState(States.ALIVE);
        }

    }
}
