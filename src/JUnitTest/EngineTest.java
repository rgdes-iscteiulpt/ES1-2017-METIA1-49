package JUnitTest;

import org.junit.jupiter.api.Test;

import antiSpamFilter.Engine;
import antiSpamFilter.GUI;

class EngineTest {

	@Test
	 public void testEngine() {
		new GUI();
	}
	
	@Test
	public void testMain() {
		Engine.main(null);	    
	}

}
