package de.hhn.se.labsw.gdt.server.model;

import java.util.ArrayList;

import de.hhn.se.labsw.gdt.library.*;
import de.hhn.se.labsw.gdt.library.Player;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class used to exchange the state of a game. All properties are
 * inherited from GameState.
 * 
 * @author Maximilian Roeck
 *
 */
@XmlRootElement
public class Model extends GameState {
	/**
	 * Constructor using a GameState object to create a model.
	 * 
	 * @param gs
	 * 			the game state the model is supposed to be created from
	 */
	public Model(GameState gs) {
		this.activePlayer = gs.getActivePlayer();
		this.phaseRoundStatus = gs.getPhaseRoundStatus();
		this.players = gs.getPlayers();
		this.map = gs.getMap();
		this.host = gs.getHost();	
	}
	
}