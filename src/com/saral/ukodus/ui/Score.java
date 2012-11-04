/**
 * 
 */
package com.saral.ukodus.ui;

import android.widget.TextView;

/**
 * @author Ali
 *
 */
public class Score {
	
	private int sum = 0;
	
	private final TextView view;

	public Score(TextView view) {
		super();
		this.view = view;
	}

	public static int calculate(int[] numbers) {
		int sum = 0;
		for (int number : numbers) {
			sum += number * howOften(number, numbers);
		}
		return sum;
	}

	private static int howOften(int number, int[] numbers) {
		int counter = 0;
		for (int candidate : numbers) {
			if (candidate==number) {
				counter++;
			}
		}
		return counter;
	}

	public int getSum() {
		return sum;
	}
	
	public void add(int[] numbers) {
		sum += calculate(numbers);
		view.setText(Integer.toString(sum));
	}
}
