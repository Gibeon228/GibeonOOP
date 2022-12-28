package com.mygdx.game.chars;

import java.util.List;

public class Monk extends Healer {
    public Monk(List<NPC> team, int x, int y) {
        super(12, 7, new int[]{-4, -4}, 30.0, 5, States.ALIVE);
        super.myTeam = team;
        super.position = new Vector2(x, y);
    }
}