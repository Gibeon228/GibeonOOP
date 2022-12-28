package com.mygdx.game.chars;

import java.util.List;

public class Sniper extends Shooter {
    public Sniper(List<NPC> team, int x, int y) {
        super(12, 10, new int[]{8, 10}, 15.0, 9, States.ALIVE, 32);
        super.myTeam = team;
        super.position = new Vector2(x, y);
    }

    public String getInfo() {
        return super.getInfo();
    }
}
