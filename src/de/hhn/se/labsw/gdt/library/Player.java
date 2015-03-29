package de.hhn.se.labsw.gdt.library;

import java.awt.Color;

public class Player extends PlayerState implements PlayerDefaults{

	private Color color = COLOR_DEFAULT;
	private User user = null;
	private String playerName = PLAYER_NAME_DEFAULT;
	private int ap = AP_DEFAULT;
	private int score = 0;
}
