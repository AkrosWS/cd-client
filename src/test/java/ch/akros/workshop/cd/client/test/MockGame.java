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

	private volatile Map<Player, AtomicBoolean> players = new HashMap<Player, AtomicBoolean>();

	@Override
	public void subscribe(Player player) {
		players.put(player, new AtomicBoolean(true));
		logger.info("subscribe for Player 0 " + player.getName() + " called");
	}

	public boolean didPlayerSubscribe(Player player) {
		AtomicBoolean subscription = players.get(player);
		return subscription != null ? subscription.get() : false;
	}

	@Override
	public void run() throws NotEnoughPlayerException, GameAlreadyInPlayException {
		throw new IllegalAccessError("run is not implemented in the mock");

	}
}
