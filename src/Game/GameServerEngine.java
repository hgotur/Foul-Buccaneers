package Game;

public class GameServerEngine {
  private GameController game;
  
  public Boolean started = false;
  
  public GameServerEngine(GameController theGame) {
    game = theGame;
  }
  
  public void addPlayer(String playerName, int playerPosition) {
    game.waitingRoom.addPlayer(playerName, playerPosition);
  }
}
