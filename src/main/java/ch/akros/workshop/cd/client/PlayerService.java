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
import ch.akros.workshop.cd.domain.Player;

@Startup
@Remote
@LocalBean
@Singleton
public class PlayerService implements Player {
	private static final String name = "Player 7";
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
		game.subscribe("java:global/cd-client7/PlayerService!ch.akros.workshop.cd.domain.Player", this.getName());

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

}
