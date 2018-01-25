package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

class Board {
	private int[][] board = new int[Constants.ROW][Constants.COL]; 
	private int bkt_x;
	private int b_x,b_y;
	
	public Board(){
		//making borders
		for(int i=0;i<Constants.ROW;i++){
			board[i][0] = 1;
			board[i][Constants.COL-1] = 1;
			if(i < 12 ){
				board[0][i] = 1;
				board[Constants.ROW-1][i] = 1;
			}
		}
		bkt_x = b_x = b_y = 1;
	}
	
	public void clear(){
		for(int i=1;i<Constants.ROW-1;i++){
			for(int j=1;j<Constants.COL-1;j++){
				board[i][j] = 0;
			}
		}
	}
	
	public void placeBucket(Bucket bkt){
		for(int i=bkt_x;i<bkt_x+bkt.getSize();i++)
			board[1][i] = 0;
		bkt_x = bkt.getX();
		for(int i=bkt_x;i<bkt_x+bkt.getSize();i++)
			board[1][i] = 3;
	}
	
	public void placeBall(Ball b){
		board[b_x][b_y] = 0;
		b_x = b.getY();
		b_y = b.getX();
		board[b_x][b_y] = 2;
	}
	
	public void display(ShapeRenderer shapeRenderer){
		for(int i=0;i<Constants.ROW;i++){
			for(int j=0;j<Constants.COL;j++){
				if(board[i][j] == 1){
					shapeRenderer.setColor(1f,1f,1f,0f);
				}
				else if(board[i][j] == 2){
					shapeRenderer.setColor(0.9f,0.3f,0.2f,0f);
				}
				else if(board[i][j] == 3){
					shapeRenderer.setColor(0.3f,0.65f,0.2f,0f);
				}
				else{
					continue;
				}
				shapeRenderer.rect(j*Constants.B_SIZE, i*Constants.B_SIZE,
						Constants.B_SIZE, Constants.B_SIZE);
			}
		}
	}

}
