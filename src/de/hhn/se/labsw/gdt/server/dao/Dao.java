package de.hhn.se.labsw.gdt.server.dao;

import java.util.HashMap;
import java.util.Map;

import de.hhn.se.labsw.gdt.server.model.*;

public enum Dao {

	INSTANCE;
	
	private Map<String, Model> contentProvider = new HashMap<String, Model>();
	
	public Map<String, Model> getModel(){
		return contentProvider;
	}
}
