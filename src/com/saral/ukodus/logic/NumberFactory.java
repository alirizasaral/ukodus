/**
 * 
 */
package com.saral.ukodus.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Ali
 *
 */
public class NumberFactory implements ITimeAware {
	
	private final List<INumberFactoryObserver> observers = new LinkedList<INumberFactoryObserver>();
	
	private LinkedList<Integer> numbers = new LinkedList<Integer>();

	private static final int LIMIT = 12;
	
	private final int level;
	
	private int bonus = 0;
	
	public NumberFactory(int level) {
		super();
		this.level = level;
	}
	
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public void registerObserver(INumberFactoryObserver observer) {
		observers.add(observer);
	}
	
	public void ticTac() {
		synchronized (numbers) {
			if (!hasBonus() && !isFull()) {
				int number = generateNumber();
				numbers.add(number);
				notifyNewNumber(number);
			}
		}
	}
	
	private boolean hasBonus() {
		return bonus-- > 0;
	}

	private void notifyNewNumber(int number) {
		for (INumberFactoryObserver observer : observers) {
			observer.notifyNewNumber(number);
		}
	}

	public Integer consumeNumber() {
		synchronized (numbers) {
			Integer number = numbers.poll();
			if (number != null) {
				notifyNumberConsumed(number);
			}
			return number;
		}
	}
	
	private void notifyNumberConsumed(Integer number) {
		for (INumberFactoryObserver observer : observers) {
			observer.notifyNumberConsumed(number);
		}
	}

	public Integer getActiveNumber() {
		return numbers.peek();
	}

	public int generateNumber() {
		Random random = new Random();
		return level + random.nextInt(9);
	}

	public boolean isFull() {
		return numbers.size()==LIMIT;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
