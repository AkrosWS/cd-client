package ch.akros.workshop.cd.client.util;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import ch.akros.workshop.cd.client.PlayerService;

//@formatter:off
/**
* 
* Note we deliberately do not run this with CDI as the PlayerService is a EJB which would cause a problem. Solution would be to factor out the business logic. 
* 
* 1. DONE ensure that Name is never Null
* 2. DONE First call keepPlaying shall return true
* 3. DONE Second call keepPlaying shall return false
* 4. each call shall alternate starting with true
* 
* 
*/
//@formatter:on
public class PlayerServiceTest {

	@Inject
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

	@Test
	public void whenFirstCallKeepPlayingReturnTrue() {
		// Init
		testee = new PlayerService();

		// run
		boolean result = testee.keepPlaying();

		Assert.assertTrue("First call shall return true", result);
	}

	@Test
	public void whenSecondCallKeepPlayingReturnFalse() {
		// Init
		testee = new PlayerService();

		// run
		testee.keepPlaying();
		boolean result = testee.keepPlaying();

		Assert.assertFalse("Second call shall return false", result);
	}

}
