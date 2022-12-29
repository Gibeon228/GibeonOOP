package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.chars.*;

import java.util.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Map<String, MyAtlasAnim> animMap = new HashMap<>();
    TextureAtlas atlas;
    public static List<NPC> leftSide = new ArrayList<>();
    public static List<NPC> rightSide = new ArrayList<>();
    static final float SCALE = 40;
    BitmapFont font;

    float time;

    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(Color.ROYAL);
        font.getData().setScale(1, 2);
        batch = new SpriteBatch();
        img = new Texture("CmBkCur.png");
        atlas = new TextureAtlas("atlas/unnamed.atlas");
        animMap.put("anim1_stand", new MyAtlasAnim(atlas, "stay", 12, Animation.PlayMode.LOOP));
        animMap.put("anim1_walk", new MyAtlasAnim(atlas, "scwalk", 12, Animation.PlayMode.LOOP));
        animMap.put("anim1_fire", new MyAtlasAnim(atlas, "shot", 12, Animation.PlayMode.LOOP));
        animMap.put("anim1_leftDeath", new MyAtlasAnim(atlas, "leftDeath", 12, Animation.PlayMode.LOOP));
        init();

    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);

        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();


        animMap.get("anim1_stand").setTime(Gdx.graphics.getDeltaTime());
        animMap.get("anim1_leftDeath").setTime(Gdx.graphics.getDeltaTime());

        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setPriority();

        for (int i = 0; i < rightSide.size(); i++) {
            if (!rightSide.get(i).fire && MathUtils.random(-10000, 100) > 90) {
                rightSide.get(i).fire = true;
                time = 0;
            }
            if (rightSide.get(i).fire)
                batch.draw(animMap.get("anim1_leftDeath").getFrame(), rightSide.get(i).getPosition().x * SCALE, rightSide.get(i).getPosition().y * SCALE);
            else
                batch.draw(animMap.get("anim1_stand").getFrame(), rightSide.get(i).getPosition().x * SCALE, rightSide.get(i).getPosition().y * SCALE);
        }

        for (int i = 0; i < leftSide.size(); i++) {
            if (!leftSide.get(i).fire && MathUtils.random(-10000, 100) > 90) {
                leftSide.get(i).fire = true;
                time = 0;
            }
            if (leftSide.get(i).fire)
                batch.draw(animMap.get("anim1_leftDeath").getFrame(), leftSide.get(i).getPosition().x * SCALE, leftSide.get(i).getPosition().y * SCALE);
            else
                batch.draw(animMap.get("anim1_stand").getFrame(), leftSide.get(i).getPosition().x * SCALE, leftSide.get(i).getPosition().y * SCALE);
        }


        time += Gdx.graphics.getDeltaTime();
        font.draw(batch, "Hello!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);


        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    private static void init() {
        rightSide = new ArrayList();
        leftSide = new ArrayList();
        int x = 1;
        int y = 1;

        int i;
        for (i = 0; i < 10; ++i) {
            switch ((new Random()).nextInt(4)) {
                case 0:
                    rightSide.add(new Peasant(rightSide, x++, y));
                    break;
                case 1:
                    rightSide.add(new Robber(rightSide, x++, y));
                    break;
                case 2:
                    rightSide.add(new Sniper(rightSide, x++, y));
                    break;
                default:
                    rightSide.add(new Monk(rightSide, x++, y));
            }
        }

        x = 0;
        y = 9;

        for (i = 0; i < 10; ++i) {
            switch ((new Random()).nextInt(4)) {
                case 0:
                    leftSide.add(new Peasant(leftSide, x++, y));
                    break;
                case 1:
                    leftSide.add(new Spearman(leftSide, x++, y));
                    break;
                case 2:
                    leftSide.add(new Crossbowman(leftSide, x++, y));
                    break;
                default:
                    leftSide.add(new Wizard(leftSide, x++, y));
            }
        }
    }

    public static void setPriority() {
        int i;
        String clazz;
        for(i = 0; i < 10; ++i) {
            clazz = ((NPC)rightSide.get(i)).getClass().toString();
            if (clazz.contains("Sniper")) {
                ((NPC)rightSide.get(i)).step(leftSide);
            }

            clazz = ((NPC)leftSide.get(i)).getClass().toString();
            if (clazz.contains("Crossbowman")) {
                ((NPC)leftSide.get(i)).step(rightSide);
            }
        }

        for(i = 0; i < 10; ++i) {
            clazz = ((NPC)rightSide.get(i)).getClass().toString();
            if (clazz.contains("Robber")) {
                ((NPC)rightSide.get(i)).step(leftSide);
            }

            clazz = ((NPC)leftSide.get(i)).getClass().toString();
            if (clazz.contains("Spearman")) {
                ((NPC)leftSide.get(i)).step(rightSide);
            }
        }

        for(i = 0; i < 10; ++i) {
            clazz = ((NPC)rightSide.get(i)).getClass().toString();
            if (clazz.contains("Monk")) {
                ((NPC)rightSide.get(i)).step(leftSide);
            }

            clazz = ((NPC)leftSide.get(i)).getClass().toString();
            if (clazz.contains("Wizard")) {
                ((NPC)leftSide.get(i)).step(rightSide);
            }
        }

        for(i = 0; i < 10; ++i) {
            clazz = ((NPC)rightSide.get(i)).getClass().toString();
            if (clazz.contains("Peasant")) {
                ((NPC)rightSide.get(i)).step(leftSide);
            }

            clazz = ((NPC)leftSide.get(i)).getClass().toString();
            if (clazz.contains("Peasant")) {
                ((NPC)leftSide.get(i)).step(rightSide);
            }
        }
    }
}
