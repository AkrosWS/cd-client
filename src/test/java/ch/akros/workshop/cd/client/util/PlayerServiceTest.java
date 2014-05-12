package ch.akros.workshop.cd.client.util;

import org.junit.Assert;
import org.junit.Test;

import ch.akros.workshop.cd.client.PlayerService;

//@formatter:off
/**
* 
* 1. DONE ensure that Name is never Null

* 
* 
*/
//@formatter:on

public class PlayerServiceTest {

	private PlayerService testee;

	@Test
	public void ensureNameIsNotNull() {
		// Init
		testee = new PlayerService();
		// run
		String name = testee.getName();
		// assert
		Assert.assertNotNull("getName sollte nie Null zurück geben", name);
	}

}
