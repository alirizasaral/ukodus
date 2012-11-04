/**
 * 
 */
package com.saral.ukodus.logic;

/**
 * @author Ali
 *
 */
public interface INumberFactoryObserver {

	public void notifyNewNumber(int number);
	
	public void notifyNumberConsumed(int number);
}
