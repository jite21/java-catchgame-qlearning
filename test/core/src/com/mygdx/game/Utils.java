package com.mygdx.game;

import java.util.Collections;
import java.util.HashMap;

import com.badlogic.gdx.math.MathUtils;

public class Utils {

	private static HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
	private static double[][] q = new double[2000][3];
	private static int ran = 0;

	public static double reward(int bx,int by,int bktx,int bktsize){
		if(by > 1) return 0;
		if(bx >= bktx && bx < bktx+bktsize ){
			return 1;
		}
		return -1;	
	}

	public static int stateToNumber(int bx,int by,int bktx){
		int k = Integer.parseInt(bx + "" + by + "" +bktx);
		if(hm.containsKey(k)){
			return hm.get(k);
		}
		if(!hm.isEmpty()){
			hm.put(k,Collections.max(hm.values()) + 1);		
		}
		else{
			hm.put(k, 1);
		}
		return hm.get(k);
	}
	
	public static int getMaxIndex(int curr){
		double mx = -9999;
		int j=0;
		for(int i =0;i < 3;i++){
            if(q[curr][i] > mx) {
            	mx = q[curr][i];
            	j = i;
            }
        }
		return j;
	}
	
	public static int qLearn(Ball b,Bucket bkt,double g,double l,double r,int s){
		int curr = stateToNumber(b.getX(),b.getY(),bkt.getX());
		int a = getMaxIndex(curr);
		if(MathUtils.random(26000) < (250 - s)){
			a = MathUtils.random(1,2);
			System.out.println("Random Move " + ran++);
		}
		int nxt = stateToNumber(b.getX(),b.nxt_state(),bkt.nxt_state(a));
		double mx = -9999;
		for(int i =0;i < 3;i++){
            if(q[nxt][i] > mx) mx = q[nxt][i];
        }
		q[curr][a] += l * (r + g * mx - q[curr][a]);
		return a;
	}

}
