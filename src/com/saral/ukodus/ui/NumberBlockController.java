/**
 * 
 */
package com.saral.ukodus.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.saral.ukodus.R;
import com.saral.ukodus.logic.INumberBlockObserver;
import com.saral.ukodus.logic.NumberBlock;
import com.saral.ukodus.logic.NumberFactory;

/**
 * @author Ali
 *
 */
public class NumberBlockController implements INumberBlockObserver {

	private final View numberBlockView;
	private final TextView[] numberFields = new TextView[4];
	
	private final NumberBlock numberBlockModel;
	
	private final NumberFactory numberFactory;

	public NumberBlockController(View numberBlockView,
			NumberBlock numberBlockModel, NumberFactory numberFactory) {
		super();
		
		this.numberFactory = numberFactory;
		
		this.numberBlockView = numberBlockView;
		numberFields[0] = (TextView) numberBlockView.findViewById(R.id.numberField1);
		numberFields[1] = (TextView) numberBlockView.findViewById(R.id.numberField2);
		numberFields[2] = (TextView) numberBlockView.findViewById(R.id.numberField3);
		numberFields[3] = (TextView) numberBlockView.findViewById(R.id.numberField4);
		
		this.numberBlockModel = numberBlockModel;
		this.numberBlockModel.registerObserver(this);
		
		numberBlockView.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				onNumberBlockClicked();
			}
		});
	}

	private void onNumberBlockClicked() {
		Integer number = numberFactory.consumeNumber();
		if (number != null) {
			numberBlockModel.putNumber(number);
			updateView();
		}
	}
	
	private void updateView() {
		int[] numbers = numberBlockModel.getNumbers();
		for (int i=0; i<numbers.length; i++) {
			if (numbers[i] != -1) {
				numberFields[i].setText(Integer.toString(numbers[i]));
			} else {
				numberFields[i].setText("");
			}
		}
	}

	public NumberBlock getNumberBlockModel() {
		return numberBlockModel;
	}

	public void notifyCountDownCompleted(int[] initialState) {
		if (NumberBlock.isSequence(initialState)) {
			numberFactory.setBonus(Game.BONUS);
		}
	}

	public void notifyStateChanged() {
		updateView();
	}
	
	
}
