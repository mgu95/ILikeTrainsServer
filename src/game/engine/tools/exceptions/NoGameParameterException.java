package game.engine.tools.exceptions;

public class NoGameParameterException extends Exception {

    public NoGameParameterException() {
        super("No parameter! Set the parameter first!");
    }
}
