package com.mygdx.game.chars;

public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getDistance(Vector2 pos) {
        return (float)Math.sqrt((double)((this.x - pos.x) * (this.x - pos.x) + (this.y - pos.y) * (this.y - pos.y)));
    }

    public boolean isEqual(Vector2 pos) {
        return pos.y == this.y & pos.x == this.x;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}