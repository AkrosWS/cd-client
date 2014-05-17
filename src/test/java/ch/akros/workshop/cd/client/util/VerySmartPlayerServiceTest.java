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
* 2. DONE Test new behavior return false if more then 3 sticks are placed
* 2a.DONE 2 Sticks return true
* 2b DONE 4 Sticks return false
* 2c DONE 3 Stick player below average return true 
* 2d DONE 3 Stick player above average return false 
* 2e DONE 3 Stick player equal average return false 
* 
*/
//@formatter:on
public class VerySmartPlayerServiceTest {

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

	@Test
	public void when2StickThenTrue() {
		// Init
		testee = new PlayerService();

		// run
		boolean result = testee.keepPlaying(new boolean[] { true, true, false, false, false }, new int[] { 1, 3, 5 }, 3);

		Assert.assertTrue("Result should be true as only 2 sticks are in the board", result);

	}

	@Test
	public void when4StickThenFalse() {
		// Init
		testee = new PlayerService();

		// run
		boolean result = testee.keepPlaying(new boolean[] { true, true, true, true, false }, new int[] { 1, 3, 5 }, 3);

		Assert.assertFalse("Result should be false as only 4 sticks are in the board", result);

	}

	@Test
	public void when3StickPlBelowAverageThenTrue() {
		// Init
		testee = new PlayerService();

		// run
		boolean result = testee.keepPlaying(new boolean[] { true, true, true, false, false }, new int[] { 1, 2, 5 }, 2);

		Assert.assertTrue("Result should be true as only 3 sticks are in the board and average is 2.6666667", result);

	}

	@Test
	public void when3StickPlAboveAverageThenFalse() {
		// Init
		testee = new PlayerService();

		// run
		boolean result = testee.keepPlaying(new boolean[] { true, true, true, false, false }, new int[] { 1, 2, 5 }, 5);

		Assert.assertFalse("Result should be false as only 3 sticks are in the board but average is 2.6666667", result);

	}

	@Test
	public void when3StickPlequalAverageThenFalse() {
		// Init
		testee = new PlayerService();

		// run
		boolean result = testee.keepPlaying(new boolean[] { true, true, true, false, false }, new int[] { 1, 3, 5 }, 3);

		Assert.assertFalse("Result should be false as only 3 sticks are in the board but average is 3", result);

	}
}
