package Game;

/**
 * Player
 * A player object storing information about
 * a player.
 */
public class Player {
	  public String name;
	  public int status;
	  
	  /**
	   * Constructor
	   * Sets object vars. If status is not
	   * provided then the default status is 0.
	   * @param playerName
	   */
	  public Player (String playerName) {
		  name = playerName;
		  status = 0;
	  }
	  
	  public Player (String playerName, int playerStatus) {
		  name = playerName;
		  status = playerStatus;
	  }
}