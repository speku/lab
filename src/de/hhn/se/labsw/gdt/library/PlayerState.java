package de.hhn.se.labsw.gdt.library;

import java.awt.Color;

/**
 * Class representing the current state of a player.
 * 
 * @author Maximilian Roeck
 *
 */
public class PlayerState {
	/**
	 * The color assigned to the player.
	 */
	protected Color color;
	/**
	 * The user associated with the player.
	 */
	protected User user;
	/**
	 * The name of the player.
	 */
	protected String name;
	/**
	 * The amount of action points of the player.
	 */
	protected int ap;
	/**
	 * The user adjusted amount of action points a player is supposed to receive per round.
	 */
	protected int apCustomized;
	/**
	 * The player's score.
	 */
	protected int score;
	/**
	 * The player's age.
	 */
	protected int age;
	/**
	 * The player's id.
	 */
	protected int id;
}
