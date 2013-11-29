package Game;

public class GameServerEngine {
  private GameController game;
  
  String [] players;
  
  public Boolean started = false;
  
  public GameServerEngine(GameController theGame) {
    game = theGame;
    
    players = new String[4];
  }
  
  public void addPlayer(String playerName, int playerPosition) {
	  players[playerPosition] = playerName;
	  
	  // send message to all clients
	  
    //game.waitingRoom.addPlayer(playerName, playerPosition);
  }
}
