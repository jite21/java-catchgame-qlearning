package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends ApplicationAdapter {
	private ShapeRenderer shapeRenderer;
	private Board board;
	private Ball b;
	private Bucket bkt;
	private int size = 2;
	private int score;
	
	public void train_loop(){
		board.placeBall(b);
		board.placeBucket(bkt);
		double r = Utils.reward(b.getX(),b.getY(),bkt.getX(),bkt.getSize());
		int act = Utils.qLearn(b, bkt, 0.8f, 0.02f, r,score);
		bkt.move(act);
		score += r == 1 ? 1:0;
		b.drop();
	}

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		board = new Board();
		b = new Ball(2);
		bkt = new Bucket(1 , size);
		score = 0;
		int i = 0;
		while(score < 2000){
			train_loop();
			System.out.println("Training " + i++ + " Score " + score);
		}
		score = 0;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		board.display(shapeRenderer);
		shapeRenderer.end();
		
		board.placeBall(b);
		board.placeBucket(bkt);
		int curr = Utils.stateToNumber(b.getX(),b.getY(),bkt.getX());
		int a = Utils.getMaxIndex(curr);
		bkt.move(a);
		b.drop();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
