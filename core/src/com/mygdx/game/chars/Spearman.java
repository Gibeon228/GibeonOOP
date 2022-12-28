package com.mygdx.game.chars;

import java.util.List;

public class Spearman extends Warrior {
    public Spearman(List<NPC> team, int x, int y) {
        super(4, 5, new int[]{1, 3}, 10.0, 4, States.ALIVE);
        super.myTeam = team;
        super.position = new Vector2(x, y);
    }

    public String getInfo() {
        return super.getInfo();
    }
}
