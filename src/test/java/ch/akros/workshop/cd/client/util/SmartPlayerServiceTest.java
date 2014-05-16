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
* 1. DONE Ensure old callback keeps working
* 2. DONE Call new Callback and ensure same behavior, test alternating scenario. 
* 		and remove later on as we want a new behavior
* 3. DONE Test new behavior return false if more then 3 sticks are placed
* 
* 
*/
//@formatter:on
public class SmartPlayerServiceTest {

	@Inject
	private PlayerService testee;

	@Test
	public void whenOldCallbackIsCallThenStillReturnSame() {
		// Init
		testee = new PlayerService();

		// run
		boolean result = testee.keepPlaying();

		Assert.assertTrue("First call shall return true", result);
	}

	// @Test Test case 2, used to verify old behavior on the new callback that changes later on
	// public void ensureAlternatingResultOnKeepPlaying() {
	// // Init
	// testee = new PlayerService();
	//
	// // run
	// boolean previousResult = false;
	// for (int i = 0; i < 100; i++) {
	// boolean result = testee.keepPlaying(new boolean[5]);
	//
	// Assert.assertTrue("Result " + i + " is not alternating, previous=" + previousResult + " current=" + result, previousResult != result);
	// previousResult = result;
	// }
	//
	// }

	@Test
	public void when3SticksReturnFalse() {
		// Init
		testee = new PlayerService();

		// run
		boolean result = testee.keepPlaying(new boolean[] { true, true, true, false, false });

		Assert.assertFalse("Result was not false", result);

	}

	@Test
	public void ensureItIsNotAlternating() {
		// Init
		testee = new PlayerService();

		// run
		boolean result1 = testee.keepPlaying(new boolean[] { true, true, true, false, false });
		boolean result2 = testee.keepPlaying(new boolean[] { true, true, true, false, false });

		Assert.assertEquals("Result still alternating", result1, result2);

	}
}
