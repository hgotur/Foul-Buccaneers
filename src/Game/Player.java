package Game;

public class Player {
	  public String name;
	  public int status;
	  
	  public Player (String playerName) {
		  name = playerName;
		  status = 0;
	  }
	  
	  public Player (String playerName, int playerStatus) {
		  name = playerName;
		  status = playerStatus;
	  }
}