package Game;

public class GameClientEngine {
	private GameController game;
	
	public void waitingStatus(String [] players, boolean [] isReady) {
		game.waitingRoom.waitingStatus(players, isReady);
	}
}
