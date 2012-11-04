/**
 * 
 */
package com.saral.ukodus.logic;

/**
 * @author Ali
 *
 */
public interface INumberBlockObserver {

	public void notifyStateChanged();
	
	public void notifyCountDownCompleted(int[] initialState);
}
