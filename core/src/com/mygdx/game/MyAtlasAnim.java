package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAtlasAnim {
    private Animation<TextureAtlas.AtlasRegion> anim;
    private float x, y, time;

    public MyAtlasAnim(TextureAtlas atlas, String name, int fps, Animation.PlayMode playMode){
        anim = new Animation<TextureAtlas.AtlasRegion>(1.0f/fps, atlas.findRegions(name));
        anim.setPlayMode(playMode);

    }

    public void setTime(float dTime) {
        time += dTime;
    }

    public TextureRegion getFrame() {
        return  anim.getKeyFrame(time);
    }
    public boolean isEnd() {
        return anim.isAnimationFinished(time);
    }

    public void zTime() {
        time = 0;
    }
}
