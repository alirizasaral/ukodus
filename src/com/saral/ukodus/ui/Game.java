/**
 * 
 */
package com.saral.ukodus.ui;

import android.widget.TextView;

import com.saral.ukodus.logic.INumberBlockObserver;


/**
 * @author Ali
 *
 */
public class Game implements INumberBlockObserver {

	public static final int BONUS = 5;
	
	private final Score score;
	
	public Game(TextView scoreView) {
		super();
		this.score = new Score(scoreView);
	}

	public void notifyCountDownCompleted(int[] initialState) {
		score.add(initialState);
	}

	public void notifyStateChanged() {
		// TODO Auto-generated method stub
		
	}
}
