/**
 * 
 */
package com.saral.ukodus.logic;

import java.util.LinkedList;
import java.util.List;

import android.os.Handler;

/**
 * @author Ali
 *
 */
public class TimeManager {

	private final List<ITimeAware> listeners = new LinkedList<ITimeAware>();

	private final Handler handler = new Handler();
	
	public boolean register(ITimeAware object) {
		return listeners.add(object);
	}
	
	public void ticTac() {
		for (ITimeAware listener : listeners) {
			listener.ticTac();
		}
	}
	
	public void start(final int interval) {
        handler.postDelayed(new Runnable() {
			
			public void run() {
				ticTac();
				handler.postDelayed(this, interval);
			}
		}, interval);
	}
}
