package com.mygdx.game.chars;

import java.util.List;

public class Wizard extends Healer {
    public Wizard(List<NPC> team, int x, int y) {
        super(17, 12, new int[]{-5, -5}, 30.0, 9, States.ALIVE);
        super.myTeam = team;
        super.position = new Vector2(x, y);
    }
}
