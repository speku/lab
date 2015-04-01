package de.hhn.se.labsw.gdt.library;





/**
 * Class representing a player playing a game.
 * 
 * @author Maximilian Roeck
 *
 */
public class Player extends PlayerState implements PlayerDefaults{
	
	/**
	 * Default Constructor.
	 */
	public Player() {
		
		// initializing with default values
		color = COLOR_DEFAULT;
		name = NAME_DEFAULT;
		ap = AP_DEFAULT;
		score = 0;
		age = AGE_DEFAULT;
	}
	
	/**
	 * Constructor offering greater customization.
	 * 
	 * @param playerName
	 * 			the name of the player
	 * @param color
	 * 			the color of the player
	 * @param ap
	 * 			the amount of action points, the player starts with
	 * @param age
	 * 			the age of the player
	 */
	public Player(String name, String color, int ap, int age, int id) {
		
		this.name = name;
		this.color = color;
		this.ap = ap;
		this.apCustomized = ap;
		this.age = age;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the players age.
	 * 
	 * @param age
	 * 			the updated age of the player
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Sets the player's score.
	 * 
	 * @param score
	 * 			the player's new score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Retrieves the score of the player.
	 * 
	 * @return
	 * 			the player's score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Increases the player's score.
	 * 
	 * @param increase
	 * 			the value the player's score is increased by
	 */
	public void increaseScore(int increase) {
		this.score += increase;
	}
	
	/**
	 * Resets the action points to the initial amount after each round.
	 */
	public void resetAp() {
		this.ap = apCustomized;
	}
	
	/**
	 * Refreshes the action points by a standard value after each round.
	 */
	public void refreshAp() {
		this.ap += apCustomized;
	}
	
	/**
	 * Increases the action points by the given amount.
	 * 
	 * @param increase
	 * 			the amount the action points are supposed to be increased by
	 */
	public void increaseAp(int increase) {
		this.ap += increase;
	}
	
	/**
	 * Voids the action points / sets them to 0.
	 */
	public void voidAp() {
		this.ap = 0;
	}
	
	/**
	 * Sets the color of a player.
	 * 
	 * @param color
	 * 			the new color of the player
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Retrieves the player's color.
	 * 
	 * @return
	 * 			the color of the player
	 */
	public String getColor() {
		return this.color;
	}
	
	/**
	 * Retrieves the age of the player.
	 * 
	 * @return
	 * 			the player's age
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * Retrieves the action points of the player.
	 * 
	 * @return
	 * 			the player's action points
	 */
	public int getAp() {
		return this.ap;
	}
	
	/**
	 * Retrieves the name of the player;
	 * 
	 * @return
	 * 			the player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of the player.
	 * 
	 * @param name
	 * 			the new name the player shall receive
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Retrieves the user associated with the player.
	 * 
	 * @return
	 * 			the user behind the player
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * Sets the user associated with the player.
	 * 
	 * @param user
	 * 			the user behind the player
	 */
	public void setUser(User user){
		this.user = user;
	}
	
	/**
	 * Sets the player's id.
	 * 
	 * @param id
	 * 			the player's id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Retrieves the player's id.
	 * 
	 * @return
	 * 			the player's id
	 */
	public int getId() {
		return this.id;
	}

	
}
