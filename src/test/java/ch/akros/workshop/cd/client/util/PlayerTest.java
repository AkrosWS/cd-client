package ch.akros.workshop.cd.client.util;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.akros.workshop.cd.client.PlayerService;

@RunWith(CdiRunner.class)
public class PlayerTest {
	@Inject
	private PlayerService testee;

	@Test
	public void testKeepPlaying() {
	}

	@Test
	public void testgetName() {
	}

}
