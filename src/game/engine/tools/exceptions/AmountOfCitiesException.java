package game.engine.tools.exceptions;

import game.engine.tools.GameRules;

public class AmountOfCitiesException extends Exception {

    public AmountOfCitiesException() {
        super("Incorrect amount of the cities! Amount of the cities should be between "
                + new GameRules().getMinProcentRatioOfCitiesToTheNumberOfFields() + "% to "
                + new GameRules().getMaxProcentRatioOfCitiesToTheNumberOfFields() + "% of the size of the Board!");
    }
}
