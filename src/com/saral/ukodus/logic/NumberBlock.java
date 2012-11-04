/**
 * 
 */
package com.saral.ukodus.logic;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ali
 *
 */
public class NumberBlock implements ITimeAware {

	private final List<INumberBlockObserver> observers = new LinkedList<INumberBlockObserver>();
	
	private int[] numbers = new int[4];
	
	private int[] initialState = new int[4];
	
	public NumberBlock() {
		resetNumbers();
	}
	
	public void registerObserver(INumberBlockObserver observer) {
		observers.add(observer);
	}
	
	private void countDown() {
		if (!isInitialStateSet()) {
			setInitialState();
		}
		if (isSequence(numbers) || isZero()) {
			resetNumbers();
		} else {
			countDown(getLowestNumber());
		}
		notifyStateChanged();
		if (isEmpty()) {
			notifyCountDownCompleted();
			resetInitialState();
		}
	}
	
	private boolean isZero() {
		for (int number : numbers) {
			if (number != 0) {
				return false;
			}
		}
		return true;
	}

	private void resetInitialState() {
		for (int i=0;i<initialState.length;i++) {
			initialState[i] = -1;
		}
	}
	
	private void setInitialState() {
		System.arraycopy(numbers, 0, initialState, 0, 4);
	}
	
	private void notifyCountDownCompleted() {
		for (INumberBlockObserver observer : observers) {
			observer.notifyCountDownCompleted(initialState);
		}
	}

	private boolean isInitialStateSet() {
		for (int number : initialState) {
			if (number != -1) {
				return true;
			}
		}
		return false;
	}
	
	public void countDown(int number) {
		for (int i=0;i<numbers.length;i++) {
			if (numbers[i]==number && numbers[i]>0) {
				numbers[i]--;
			}
		}
	}

	private void notifyStateChanged() {
		for (INumberBlockObserver observer : observers) {
			observer.notifyStateChanged();
		}
	}

	private int getFreeSlot() {
		for (int i=0;i<4;i++) {
			if (numbers[i] == -1) {
				return i;
			}
		}
		return -1;
	}

	public int getLowestNumber() {
		if (isFull()) {
			int lowest = -1;
			for (int number  : numbers) {
				if (number > 0 && (number < lowest || lowest==-1)) {
					lowest = number;
				}
			}
			return lowest;
		} else {
			return -1;
		}
	}
	
	public int[] getNumbers() {
		return numbers;
	}

	public boolean isFull() {
		for (int number : numbers) {
			if (number == -1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isEmpty() {
		for (int number : numbers) {
			if (number != -1) {
				return false;
			}
		}
		return true;
	}

	public boolean isSequence() {
		return isFull() && isSequence(numbers);
	}
	
	public static boolean isSequence(int[] numbers) {
		int lastNumber = -1;
		for (int number : numbers) {
			if (lastNumber != -1 && lastNumber != number-1) {
				return false;
			}
			lastNumber = number;
		}
		return true;
	}

	public void putNumber(int number) {
		synchronized (numbers) {
			if (!isFull()) {
				numbers[getFreeSlot()] = number;
			}			
		}
	}

	private void resetNumbers() {
		for (int i=0;i<4;i++) {
			numbers[i] = -1;
		}
	}

	public void ticTac() {
		synchronized (numbers) {
			if (isFull()) {
				countDown();
			}
		}
	}
	
}
