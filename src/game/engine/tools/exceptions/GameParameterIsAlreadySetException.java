package game.engine.tools.exceptions;

public class GameParameterIsAlreadySetException extends Exception {

    public GameParameterIsAlreadySetException() {

        super("The parameter is already set! You cannot change the rules while the game is in progress!");
    }
}
