package ch.akros.workshop.cd.client;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.akros.workshop.cd.domain.Player;

@Startup
@Stateless
@Remote
@LocalBean
public class PlayerService implements Player {
	private Logger logger = LoggerFactory.getLogger(PlayerService.class);

	public PlayerService() {
		super();
		logger.info("PlayerService created");
	}

	// @Inject
	// GameService game;

	@PostConstruct
	public void onStart() {
		logger.error("PlayerService");

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean keepPlaying() {
		// TODO Auto-generated method stub
		return false;
	}

}
