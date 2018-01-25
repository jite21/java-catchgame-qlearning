package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Ball {
	private int x,y;
	public Ball(int x){
		this.x = x;
		this.y = Constants.ROW - 2;
	}
	
	public void reset(int x){
		this.x = x;
		this.y = Constants.ROW - 2;
	}
	
	public void drop(){
		if(this.y > 1){
			this.y--;
			return ;
		}
		reset(MathUtils.random(1, Constants.COL - 2));
	}
	
	public int nxt_state(){
		if(this.y > 0){
			return this.y-1;
		}
		return this.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
