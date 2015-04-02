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
		ObservableList<String> listData = FXCollections.observableArrayList();
		if (Storage.inspectedGame != null) {
			ArrayList<Player> players = Storage.inspectedGame.getPlayers();
		
			if (players != null && players.size() > 0) {
				for (Player p : players) {
					listData.add(p.getName());
				}
			}
		}
		return listData;
	}
	
	
	public static void setupGame(String gameId, boolean inspect) {
		if (gameId != null && gameId.length() > 0) {
			GameState gs = Connector.getGame(gameId);
			if(inspect) {
				Storage.inspectedGame = gs;
			} else {
//				Storage.hostedGame = gs;
			}
		}
	}
	
	public static GameState cloneGameState(GameState gs) {
		GameState newGs = new GameState();
		newGs.setActivePlayer(gs.getActivePlayer());
		newGs.setHost(gs.getHost());
		newGs.setMap(gs.getMap());
		newGs.setPhaseRoundStatus(gs.getPhaseRoundStatus());
		newGs.setPlayers(gs.getPlayers());
		return newGs;
	}
	
	public static void refreshBrowser() {
		
	}
	
	
}