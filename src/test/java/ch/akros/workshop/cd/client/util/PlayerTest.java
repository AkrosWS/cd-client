package ch.akros.workshop.cd.client.util;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.akros.workshop.cd.client.PlayerService;

@RunWith(CdiRunner.class)
public class PlayerTest {
	@Inject
	private PlayerService testee;

	@Test
	public void testKeepPlaying() {
		for (int i = 0; i < 10; i++) {
			Assert.assertTrue("Alternating true was not set at call " + (i * 2) + 1, testee.keepPlaying());
			Assert.assertFalse("Alternating false was not set at call " + (i * 2 + 2), testee.keepPlaying());
		}

	}

	@Test
	public void testgetName() {
		Assert.assertNotNull("Player Service does not return a Name", testee.getName());
	}

}
