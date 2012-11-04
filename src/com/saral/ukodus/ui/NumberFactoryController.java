/**
 * 
 */
package com.saral.ukodus.ui;

import android.view.View;
import android.widget.TextView;

import com.saral.ukodus.R;
import com.saral.ukodus.logic.INumberFactoryObserver;
import com.saral.ukodus.logic.NumberFactory;

/**
 * @author Ali
 *
 */
public class NumberFactoryController implements INumberFactoryObserver {
	
	private final View view;
	private final TextView[] numberFields = new TextView[12];
	
	private final NumberFactory numberFactory;

	public NumberFactoryController(View view, NumberFactory numberFactory) {
		super();
		this.view = view;
		this.numberFactory = numberFactory;
		
		numberFields[0] = (TextView) view.findViewById(R.id.numberField1);
		numberFields[1] = (TextView) view.findViewById(R.id.numberField2);
		numberFields[2] = (TextView) view.findViewById(R.id.numberField3);
		numberFields[3] = (TextView) view.findViewById(R.id.numberField4);
		numberFields[4] = (TextView) view.findViewById(R.id.numberField5);
		numberFields[5] = (TextView) view.findViewById(R.id.numberField6);
		numberFields[6] = (TextView) view.findViewById(R.id.numberField7);
		numberFields[7] = (TextView) view.findViewById(R.id.numberField8);
		numberFields[8] = (TextView) view.findViewById(R.id.numberField9);
		numberFields[9] = (TextView) view.findViewById(R.id.numberField10);
		numberFields[10] = (TextView) view.findViewById(R.id.numberField11);
		numberFields[11] = (TextView) view.findViewById(R.id.numberField12);
		
		selectField(numberFields[0]);
		numberFactory.registerObserver(this);
	}
	
	public void notifyNewNumber(int number) {
		TextView field = getFreeField();
		if (field != null) {
			field.setText(Integer.toString(number));
			field.setTag(number);
		} // TODO: game over if null
	}

	private TextView getFreeField() {
		for (TextView candidate : numberFields) {
			if (candidate.getTag() == null) {
				return candidate;
			}
		}
		return null;
	}

	public void notifyNumberConsumed(int number) {
		shiftView();
	}

	private void shiftView() {
		for (int i=0;i<numberFields.length-1;i++) {
			numberFields[i].setText(numberFields[i+1].getText());
			numberFields[i].setTag(numberFields[i+1].getTag());
		}
		resetField(numberFields[numberFields.length-1]);
	}

	private void selectField(TextView field) {
		field.setBackgroundColor(field.getResources().getColor(R.color.black));
		field.setTextColor(field.getResources().getColor(R.color.grey));
	}
	
	private void resetField(TextView field) {
		field.setTextColor(field.getResources().getColor(R.color.black));
		field.setText(null);
		field.setTag(null);
		field.setBackgroundColor(field.getResources().getColor(R.color.grey));
	}

	public View getView() {
		return view;
	}

	public NumberFactory getNumberFactory() {
		return numberFactory;
	}	
}
