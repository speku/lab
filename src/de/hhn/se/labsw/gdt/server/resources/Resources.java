package de.hhn.se.labsw.gdt.server.resources;

import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import de.hhn.se.labsw.gdt.server.dao.*;
import de.hhn.se.labsw.gdt.library.*;

/**
 * Class that acts as an entry points for client-side HTTP requests.
 * 
 * @author Maximilian Roeck
 *
 */
@Path("resources")
public class Resources {
	
	/**
	 * Retrieves the specified game from the storage.
	 * 
	 * @param gameId
	 * 			the ID of the game to be retrieved. IDs usually equate to the name of the host
	 * 			of the game
	 * @return
	 * 			a Model object that is easily translatable to a Game instance
	 */
	@GET
	@Path("games/{gameId}")
	@Produces(MediaType.APPLICATION_JSON)
	public GameState getGameState(@PathParam("gameId") String gameId) {
		return Dao.instance.getContentInstance(gameId);
	}

	/**
	 * Retrieves all games from the storage.
	 * Useful for generating content for a game browser.
	 * 
	 * @return
	 * 			all games currently in the storage represented as instances of type Model
	 */
	@GET
	@Path("games")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, GameState> getGames() {
		return Dao.instance.getContent();
	}
	
	/**
	 * Updates an existing game Model on the storage or creates a new one, if it was not already present.
	 * 
	 * @param gameId
	 * 			the ID of the game to be updated/created (usually the name of the host)
	 * @param model
	 * 			the new model data
	 */
	@POST
	@Path("games/{gameId}")
	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON) // only for debugging purposes 
	public void setGameState(@PathParam("gameId") String gameId, GameState gs) {
		Dao.instance.putContentInstance(gameId, gs);
	}
	
	// All other operations will be performed via server-sent events.
	// This greatly simplifies the process of synchronizing the clients connected to the server in 
	// regards to updates of the data model that is stored on the server.
	// Client views can then be refreshed after each "move" of a player instead of each "turn".
	// Subscribing to such events will be handled by the class BroadcasterResource.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
