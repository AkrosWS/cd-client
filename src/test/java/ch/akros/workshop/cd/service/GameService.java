package ch.akros.workshop.cd.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.akros.workshop.cd.domain.Game;
import ch.akros.workshop.cd.domain.Player;
import ch.akros.workshop.cd.exception.GameAlreadyInPlayException;
import ch.akros.workshop.cd.exception.NotEnoughPlayerException;

@Remote
@Startup
@Singleton
public class GameService implements Game, GameTestInterface {
	private Logger logger = LoggerFactory.getLogger(Game.class);

	private volatile Map<String, AtomicBoolean> players = new HashMap<String, AtomicBoolean>();

	@Override
	public void subscribe(String jndiName, String playerName) {
		players.put(jndiName, new AtomicBoolean(true));
		logger.info("subscribe for Player 0 " + playerName + " called");
	}

	@Override
	public boolean didPlayerSubscribe(Player player) {
		AtomicBoolean subscription = players.get(player.getName());
		return subscription != null ? subscription.get() : false;
	}

	@Override
	public void run() throws NotEnoughPlayerException, GameAlreadyInPlayException {
		throw new IllegalAccessError("run is not implemented in the mock");

	}

}
