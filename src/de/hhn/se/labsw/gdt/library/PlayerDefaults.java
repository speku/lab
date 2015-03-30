package de.hhn.se.labsw.gdt.library;

import java.awt.Color;

/**
 * Interface that holds the default values of the attributes of a player.
 * 
 * @author Maximilian Roeck
 *
 */
public interface PlayerDefaults {
	/**
	 * The default color assigned to a newly instantiated player object.
	 */
	public static final Color COLOR_DEFAULT = Color.WHITE;
	/**
	 * The default String value assigned to a new player's name.
	 */
	public static final String NAME_DEFAULT = "unassigned";
	/**
	 * The initial amount of action points of a newly instantiated player object.
	 */
	public static final int AP_DEFAULT = 5;
	/**
	 * The initial age of a newly created player. -1 indicates that no age has been assigned yet.
	 */
	public static final int AGE_DEFAULT = -1;
}