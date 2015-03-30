package de.hhn.se.labsw.gdt.library;

/**
 * Class representing the user behind a player.
 * 
 * @author Maximilian Roeck
 *
 */
public class User {

	/**
	 * The user's name.
	 */
	private String name = "unassigned";
	
	/**
	 * Constructor taking the user's name as an argument.
	 * 
	 * @param name
	 * 			the name of the user
	 */
	public User(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method for the user's name.
	 * 
	 * @return
	 * 			the user's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter method for the user's name.
	 * 
	 * @param name
	 * 			the name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}
}
