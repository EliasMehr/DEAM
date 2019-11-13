package ServerProgram;

public class Game {

    PlayerServer playerOne;
    PlayerServer playerTwo;
    Protocol protocol = new Protocol();

    public Game(PlayerServer playerOne, PlayerServer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }
}
