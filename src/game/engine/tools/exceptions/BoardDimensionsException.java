package game.engine.tools.exceptions;

import game.engine.tools.GameRules;

public class BoardDimensionsException extends Exception {

    public BoardDimensionsException() {

        super("Incorrect board height! The height of the board should be between "
                + new GameRules().getMinBoardHeight() + " to " + new GameRules().getMaxBoardHeight()
                + "! The width should be between " + new GameRules().getMinBoardWidth() + " to "
                + new GameRules().getMaxBoardWidth() + "!");
    }
}
