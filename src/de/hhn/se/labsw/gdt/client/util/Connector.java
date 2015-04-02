package de.hhn.se.labsw.gdt.client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.sse.EventListener;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.EventSource;
import org.glassfish.jersey.media.sse.InboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

import de.hhn.se.labsw.gdt.client.controller.Controller;
import de.hhn.se.labsw.gdt.client.gui.MainMenu;
import de.hhn.se.labsw.gdt.library.*;

/**
 * Connector to the server offering according methods to clients.
 * 
 * @author Maximilian Roeck
 *
 */
public final class Connector {

	/**
	 * Client configuration for enabling conversion by jenson used for synchronous connections.
	 */
	private static  ClientConfig clientConfig = new ClientConfig(GensonJsonConverter.class);
	
	
	
	/**
	 * Client instance used for asynchronous connections.
	 */
//	private static final Client sseClient = ClientBuilder.newClient(clientConfig).register(SseFeature.class);
	private static final Client sseClient = ClientBuilder.newBuilder().register(SseFeature.class).register(GensonJsonConverter.class).build();
//	private static  Client sseClient = ClientBuilder.newBuilder().register(SseFeature.class).build();
	
	
	
	/**
	 * Client based on a genson-specific configuration used for synchronous connections.
	 */
	private static  Client client = ClientBuilder.newClient(clientConfig);
	/**
	 * Root WebTarget for the application for non SSE-communication.
	 */
	private static WebTarget webTarget = client.target("http://localhost:8080/de.hhn.se.labsw.gdt.server/api");
//	/**
//	 * Root WebTarget for SSE.
//	 */
//	private static WebTarget sseWebTarget = sseClient.target("http://localhost:8080/de.hhn.se.labsw.gdt.server/api");
	/**
	 * Root WebTarget for all resources.
	 */
	private static WebTarget resourcesWebTarget = webTarget.path("resources");
	/**
	 * WebTarget for registering clients as broadcast-receivers and broadcasters.
	 */
//	private static WebTarget broadcastsWebTarget = sseWebTarget.path("broadcasts");
//	/**
//	 * WebTarget for retrieving all games.
//	 */
	private static WebTarget gamesWebTarget = resourcesWebTarget.path("games");
	/**
	 * WebTarget used for SSE and asynchronous connections.
	 */
	private static WebTarget sseWebTarget = sseClient.target("http://localhost:8080/de.hhn.se.labsw.gdt.server/api/broadcasts");
	/**
	 * EventSource for SSE - has socket functionality.
	 */
//	private static EventSource sseEventSource = EventSource.target(sseWebTarget).build();
	
	private static EventSource sseEventSource = new EventSource(sseWebTarget) {
	    @Override
	    public void onEvent(InboundEvent inboundEvent) {
	    	if (inboundEvent.getId() == null || !inboundEvent.getId().equals("delete")) {
	    		
	    	  
		    	GameState gs = inboundEvent.readData(GameState.class);
		    	controllerGs = gs;
	    	}
	    	else {
	    		  System.out.println("delete message received");
	    	}
	    	
	    	 if (Controller.getMainMenu() != null && Controller.getMainMenu().isShowing()) { 
		    		
		    		Platform.runLater(new Runnable() {
		    		    @Override
		    		    public void run() {
		    		    	((MainMenu)Controller.getMainMenu()).refreshBrowser();
		    		    }
		    		});
		    	}
		    
	    	} 
	    
	    
	};
	
//	private static EventListener sseListener = new EventListener() {
//        @Override
//        public void onEvent(InboundEvent inboundEvent) {
//        	GameState gs = inboundEvent.readData(GameState.class);
//        	// Controller should hold static reference to a GameObject.
//        	// The Game Object can then be updated with the new game state
//        	// which in turn should lead to an invocation of a notify method
//        	// that informs the View about the changes so that it can be redrawn.
//        	
//        	//Controller.updateGameState(gs);
//        	
//        	// debugging
//        	controllerGs = gs;
//        	System.out.println("listener responded");
//        }
//    };
	
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
	 * 			the ID (= username of host) of the game
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
		// post the game state
		gamesWebTarget.path(gameId).request().post(Entity.entity(gs, MediaType.APPLICATION_JSON));
		// notify other clients (kinda like MVC - but distributed)
		broadcastUpdates(gs); 
		// The updates are directly broadcast to the other clients without checking 
		// for consistency with the game state that has been stored on the server.
		// Since exactly the same arguments are sent this should be a non-issue but still feels like a sloppy
		// solution.
	}
	
	public static void deleteGame(String gameId, GameState gs) {
		// post the game state
		gamesWebTarget.path(gameId).request().delete();
		// notify other clients (kinda like MVC - but distributed)
		broadcastDeletes(); 
		// The updates are directly broadcast to the other clients without checking 
		// for consistency with the game state that has been stored on the server.
		// Since exactly the same arguments are sent this should be a non-issue but still feels like a sloppy
		// solution.
	}
	
	public static void broadcastDeletes() {
		sseWebTarget.request().delete();
	}
	
	/**
	 * Broadcasts updates to the game state to all clients registered with the server.
	 * 
	 * @param gs
	 * 			the updated game state to be broadcast
	 */
	public static void broadcastUpdates(GameState gs) {
		sseWebTarget.request().post(Entity.entity(gs, "application/json"));
	}
	
	/**
	 * Registers the client with the server.
	 */
	public static void registerWithServer() {
		// register client with server
		sseWebTarget.request().get();
//		// register listener to "socket"
//		sseEventSource.register(sseListener);
//		// open "socket"
//		sseEventSource.open();
	}
	
	/**
	 * De-registers the client from the server. Supposedly invoked when the game is finished.
	 */
	public static void deregisterWithServer() {
		sseEventSource.close();
	}
	
	// #################################################################################################
	// debugging
	// #################################################################################################
	
	/**
	 * Debugging field for debugSse().
	 */
	private static GameState controllerGs;
	
	/**
	 * Debugging method for server-sent events.
	 */
	public static void debugSse() {
		//registerWithServer();
		GameState gs;
		String[] users = {"Horst", "Heinz", "Heinrich", "Herbert", "Hubertus", "Adalbert"};
		for (String user : users) {
			gs = new GameState();
			gs.setHost(new User(user));
			gs.setPlayers(new ArrayList<Player>());
			gs.addPlayer(new Player(user));
			gs.addPlayer(new Player("testPlayer"));
			postGame(gs.getHost().getName(), gs);
			System.out.println("Game State sent for user \t\t" + user + " = " + getGame(user).getHost().getName());
			try{
				Thread.currentThread().sleep(500);
			} catch(Exception e) {
			
			}
			System.out.println("Game state received for user \t\t" + controllerGs.getHost().getName());
			System.out.println(controllerGs.getHost().getName());
		}
	}
	
	
	public static void main(String[] args) {
		debugSse();
	}
	
	
	
	
	
	
}
