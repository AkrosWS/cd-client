package ch.akros.workshop.cd.client;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.akros.workshop.cd.domain.Player;

@Startup
@Remote
@LocalBean
@Singleton
public class PlayerService implements Player {
	private Logger logger = LoggerFactory.getLogger(PlayerService.class);

	public PlayerService() {
		super();
		logger.info("PlayerService created");
	}

	@PostConstruct
	public void onStart() {
		logger.info("onStart");

	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean keepPlaying() {
		return false;
	}

}
