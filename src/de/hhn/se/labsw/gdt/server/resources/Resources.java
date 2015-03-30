package de.hhn.se.labsw.gdt.server.resources;

import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import de.hhn.se.labsw.gdt.server.model.*;
import de.hhn.se.labsw.gdt.server.dao.*;
import de.hhn.se.labsw.gdt.library.*;


@Path("resources")
public class Resources {
	
	/**
	 * Retrieves the specified game from the storage.
	 * 
	 * @param gameId
	 * 			the ID of the game to be retrieved. IDs usually equate to the name of the host
	 * 			of the game
	 * @return
	 * 			a game instance
	 */
	@GET
	@Path("games/{gameId}")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Model getGameState(@PathParam("gameId") String gameId) {
		return Dao.instance.getContentInstance(gameId);
	}

	
	/**
	 * Retrieves all games from the storages.
	 * Useful for generating content for a game browser.
	 * 
	 * @return
	 * 			all games currently in the storage
	 */
	@GET
	@Path("games")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Model> getGames() {
		return Dao.instance.getContent();
	}
	
	
	
	
	

}
