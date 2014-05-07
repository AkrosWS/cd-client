package ch.akros.workshop.cd.client.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.akros.workshop.cd.domain.Game;
import ch.akros.workshop.cd.domain.Player;
import ch.akros.workshop.cd.exception.GameAlreadyInPlayException;
import ch.akros.workshop.cd.exception.NotEnoughPlayerException;

public class MockGame implements Game {
	private Logger logger = LoggerFactory.getLogger(Game.class);

	private volatile Map<String, AtomicBoolean> players = new HashMap<String, AtomicBoolean>();

	@Override
	public void subscribe(String jndiName, String playerName) {
		players.put(jndiName, new AtomicBoolean(true));
		logger.info("subscribe for Player 0 " + playerName + " called");
	}

	public boolean didPlayerSubscribe(Player player) {
		AtomicBoolean subscription = players.get(player.getName());
		return subscription != null ? subscription.get() : false;
	}

	@Override
	public void run() throws NotEnoughPlayerException, GameAlreadyInPlayException {
		throw new IllegalAccessError("run is not implemented in the mock");

	}
}
