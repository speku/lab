package de.hhn.se.labsw.gdt.client.connector;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

import de.hhn.se.labsw.gdt.library.*;


public final class Connector {

	/**
	 * Client configuration for enabling conversion by jenson.
	 */
	private static final ClientConfig clientConfig = new ClientConfig(GensonJsonConverter.class);
	/**
	 * Client based on a genson-specific configuration.
	 */
	private static final Client client = ClientBuilder.newClient(clientConfig);
	/**
	 * Root WebTarget for the application.
	 */
	private static WebTarget webTarget = client.target("http://localhost:8080/de.hhn.se.labsw.gdt.server/api");
	/**
	 * Root WebTarget for all resources.
	 */
	private static WebTarget resourcesWebTarget = webTarget.path("resources");
	/**
	 * WebTarget for registering clients as broadcast-receivers and broadcasters.
	 */
	private static WebTarget broadcastsWebTarget = webTarget.path("broadcasts");
	/**
	 * WebTarget for retrieving all games.
	 */
	private static WebTarget gamesWebTarget = resourcesWebTarget.path("games");
	
	private static WebTarget postGameWebTarget;
	
	/**
	 * Retrieves a HashMap with all Games currently stored/running on the server.
	 * 
	 * @return
	 * 			HashMap of all Games on the server represented as GameState objects
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, GameState> getGames() {
		return (HashMap<String, GameState>)gamesWebTarget.request(MediaType.APPLICATION_JSON).get(HashMap.class);
	}
	/**
	 * Retrieves a specific game state from the server.
	 * 
	 * @param gameId
	 * 			the ID (=username of host) of the game
	 * @return
	 * 			the state of the game
	 */
	public static GameState getGame(String gameId) { // gameId = the name of the host of the game 
		return gamesWebTarget.path(gameId).request(MediaType.APPLICATION_JSON).get(GameState.class);
	}
	/**
	 * Posts game states to the server.
	 * 
	 * @param gameId
	 * 			the ID of the game state to be posted
	 * @param gs
	 * 			the game state to be posted
	 */
	public static void postGame(String gameId, GameState gs) {
		gamesWebTarget.path(gameId).request().post(Entity.entity(gs, MediaType.APPLICATION_JSON));
	}
	
	public static void main(String[] args) {
		GameState gs = new GameState();
		gs.setHost(new User("Horst"));
		postGame(gs.getHost().getName(), gs);
		System.out.println(getGame("Horst").getHost().getName());
	}
	
	
	
	
}
