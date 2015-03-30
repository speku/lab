package de.hhn.se.labsw.gdt.server.dao;

import java.util.HashMap;
import java.util.Map;

import de.hhn.se.labsw.gdt.library.*;
import de.hhn.se.labsw.gdt.server.model.*;

public enum Dao {

	instance;
	
	private Map<String, Model> contentProvider = new HashMap<String, Model>();
	
	private Dao() {
		GameState gs = new Game(8, 8, new int[]{4,3,3}, new User("Hans"));
		Model m = new Model(gs);
		contentProvider.put(gs.getHost().getName(), m);
	}
	
	public Map<String, Model> getContent(){
		return contentProvider;
	}
	
	public Model getContentInstance(String key) {
		return contentProvider.get(key);
	}
}
