package com.saral.ukodus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.saral.ukodus.logic.NumberBlock;
import com.saral.ukodus.logic.NumberFactory;
import com.saral.ukodus.logic.TimeManager;
import com.saral.ukodus.ui.Game;
import com.saral.ukodus.ui.NumberBlockController;
import com.saral.ukodus.ui.NumberFactoryController;

public class Main extends Activity {
	
	private final NumberBlockController[] numberBlockControllers = new NumberBlockController[6];
	
	private NumberFactoryController numberFactoryController;
	
	private final TimeManager timeManager = new TimeManager();
	
	private Game game;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        game = new Game((TextView) this.findViewById(R.id.score));
        
        numberFactoryController = new NumberFactoryController(findViewById(R.id.numberBase), new NumberFactory(3));
        
        numberBlockControllers[0] = new NumberBlockController(findViewById(R.id.numberBlock1), new NumberBlock(), numberFactoryController.getNumberFactory());
        numberBlockControllers[1] = new NumberBlockController(findViewById(R.id.numberBlock2), new NumberBlock(), numberFactoryController.getNumberFactory());
        numberBlockControllers[2] = new NumberBlockController(findViewById(R.id.numberBlock3), new NumberBlock(), numberFactoryController.getNumberFactory());
        numberBlockControllers[3] = new NumberBlockController(findViewById(R.id.numberBlock4), new NumberBlock(), numberFactoryController.getNumberFactory());
        numberBlockControllers[4] = new NumberBlockController(findViewById(R.id.numberBlock5), new NumberBlock(), numberFactoryController.getNumberFactory());
        numberBlockControllers[5] = new NumberBlockController(findViewById(R.id.numberBlock6), new NumberBlock(), numberFactoryController.getNumberFactory());
        
                               
        timeManager.register(numberFactoryController.getNumberFactory());
        for (NumberBlockController controller : numberBlockControllers) {
        	timeManager.register(controller.getNumberBlockModel());
        	controller.getNumberBlockModel().registerObserver(game);
        }
        
        timeManager.start(1000);
        
    }
}