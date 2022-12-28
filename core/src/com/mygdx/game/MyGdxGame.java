package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.chars.NPC;
import com.mygdx.game.chars.Peasant;
import com.mygdx.game.chars.Vector2;

import java.util.ArrayList;
import java.util.List;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Map<String, MyAtlasAnim> animMap = new HashMap<>();
	TextureAtlas atlas;
	List<NPC> leftSide = new ArrayList<>();
	List<NPC> rightSide = new ArrayList<>();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		atlas = new TextureAtlas("atlas/unnamed.atlas");
		animMap.put("anim1_stand", new MyAtlasAnim(atlas, "stay", 12, Animation.PlayMode.LOOP));
		animMap.put("anim1_walk", new MyAtlasAnim(atlas, "scwalk", 12, Animation.PlayMode.LOOP));

		Vector2 pos1 = new Vector2(1, 1);
		Vector2 pos2 = new Vector2(1, 10);
		for (int i = 0; i < 10; i++) {
			leftSide.add(new Peasant(rightSide, 1, 1));
			rightSide.add(new Peasant(leftSide, 1, 10));

			pos1.x++;
			pos2.x++;
		}

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		float mx = Gdx.input.getX();
		float my = Gdx.graphics.getHeight() - Gdx.input.getY();


		animMap.get("anim1_stand").setTime(Gdx.graphics.getDeltaTime());

		batch.begin();
		for (NPC hero : rightSide) {
			batch.draw(animMap.get("anim1_stand").getFrame(), hero.getPosition().x, hero.getPosition().y);
		}
		for (NPC hero : leftSide) {
			batch.draw(animMap.get("anim1_stand").getFrame(), hero.getPosition().x, hero.getPosition().y);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
