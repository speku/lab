package de.hhn.se.labsw.gdt.client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import de.hhn.se.labsw.gdt.library.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Utility {

	public static ObservableList<String> getBrowserData() {
		HashMap<String, GameState> games = (HashMap<String, GameState>)Connector.getGames();
		ObservableList<String> listData = FXCollections.observableArrayList();
		Set<String> gameIds = games.keySet();
		for (String s : gameIds) {
			listData.add(s);
		}
		return listData;
	}
	
	public static ObservableList<String> getGameData() {
//		List<String> playerNames = new ArrayList<String>();
		ObservableList<String> listData = FXCollections.observableArrayList();
		if (Storage.inspectedGame != null) {
			ArrayList<Player> players = Storage.inspectedGame.getPlayers();
		
			if (players != null && players.size() > 0) {
				for (Player p : players) {
					listData.add(p.getName());
		//			playerNames.add(p.getName());
				}
			}
//		Storage.inspectedPlayers = playerNames;
		}
		listData.add("test");
		return listData;
	}
	
	
	public static void setupGame(String gameId, boolean inspect) {
		if (gameId != null && gameId.length() > 0) {
			GameState gs = Connector.getGame(gameId);
			if(inspect) {
				Storage.inspectedGame = new Game(gs);
			} else {
				Storage.hostedGame = new Game(gs);
			}
		}
	}
	
	public static void getPlayerData(String name) {
		
		
	}
}
