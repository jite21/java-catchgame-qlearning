package com.mygdx.game;

public class Bucket {
	private int x,size;
	public Bucket(int x,int size){
		this.x = x;
		this.size = size;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public boolean move(int a){
		switch(a){
			case 1:
				if((this.x + this.size) < Constants.COL - 1){
					this.x++;
					return true;
				}
				break;
			case 2:
				if(this.x > 1){
					this.x--;
					return true;
				}
		}
		return false;
	}
	
	public int nxt_state(int a){
		switch(a){
			case 1:
				if((this.x + this.size) < Constants.COL - 1)
					return this.x+1;
				break;
			case 2:
				if(this.x > 1)
					return this.x-1;
		}
		return this.x;
	}
}
