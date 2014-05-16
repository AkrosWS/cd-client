package ch.akros.workshop.cd.client;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.akros.workshop.cd.domain.Game;
import ch.akros.workshop.cd.domain.SmartPlayer;

@Startup
@Remote
@LocalBean
@Singleton
public class PlayerService implements SmartPlayer {
	private static final String name = "Player 1";
	private Logger logger = LoggerFactory.getLogger(PlayerService.class);
	private volatile boolean keepPlaying = false;

	public PlayerService() {
		super();
		logger.info("PlayerService created");
	}

	@EJB(lookup = "java:global/cd/GameService!ch.akros.workshop.cd.domain.Game")
	private Game game;

	@PostConstruct
	public void onStart() {
		logger.info("onStart");
		game.subscribe("java:global/cd-client/PlayerService!ch.akros.workshop.cd.domain.SmartPlayer", this.getName());

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean keepPlaying() {
		keepPlaying = !keepPlaying;
		return keepPlaying;
	}

	@Override
	public boolean keepPlaying(boolean[] board) {
		int stickCount = 0;
		for (int i = 0; i < 5; i++) {
			if (board[i]) {
				stickCount++;
			}
		}
		return stickCount < 3;
	}

}
