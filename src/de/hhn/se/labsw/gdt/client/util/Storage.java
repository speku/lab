package de.hhn.se.labsw.gdt.client.util;

import java.util.List;
import java.util.Set;

import de.hhn.se.labsw.gdt.library.*;

public class Storage {

	public static User user = new User("unassigned");
	public static GameState activeGame;
	public static GameState inspectedGame;
	public static boolean gameHosted = false;
	public static Player playerOfUser;
//	public static List<String> inspectedPlayers;
//	public static String inspectedGameId;
	
}