package de.hhn.se.labsw.gdt.server.dao;

import java.util.HashMap;
import java.util.Map;

import de.hhn.se.labsw.gdt.library.*;


public enum Dao {

	instance;
	
	private Map<String, GameState> contentProvider = new HashMap<String, GameState>();
	
	private Dao() {
//		GameState gs = new Game(8, 8, new int[]{4,3,3}, new User("Hans"));
//		contentProvider.put(gs.getHost().getName(), gs);
	}
	
	public Map<String, GameState> getContent(){
		return contentProvider;
	}
	
	public GameState getContentInstance(String key) {
		return contentProvider.get(key);
	}
	
	public void putContentInstance(String key, GameState value) {
		contentProvider.put(key, value);
	}
}
