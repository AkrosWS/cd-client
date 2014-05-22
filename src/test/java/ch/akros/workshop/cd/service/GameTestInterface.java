package ch.akros.workshop.cd.service;

import ch.akros.workshop.cd.domain.Game;
import ch.akros.workshop.cd.domain.Player;

public interface GameTestInterface extends Game {

	public abstract boolean didPlayerSubscribe(Player player);

}
