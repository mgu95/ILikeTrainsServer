package game.engine.tools;

import game.engine.tools.exceptions.AmountOfCitiesException;
import game.engine.tools.exceptions.BoardDimensionsException;
import game.engine.tools.exceptions.GameParameterIsAlreadySetException;
import game.engine.tools.exceptions.NoGameParameterException;

public class GameRules {

    Loger loger = new ConsoleLoger(false);

    private final int minBoardHeight = 10;
    private final int maxBoardHeight = 20;
    private final int minBoardWidth = 20;
    private final int maxBoardWidth = 30;
    private final int minProcentRatioOfCitiesToTheNumberOfFields = 10;
    private final int maxProcentRatioOfCitiesToTheNumberOfFields = 20;
    private final int minPlayers = 2;
    private final int maxPlayers = 5;
    private final String[] routeColors = new String[]{"Red", "Yellow", "Blue", "Orange", "Pink", "Grey"};
    private final String[] cardColors = new String[]{"Red", "Yellow", "Blue", "Orange", "Pink", "Rainbow"};

    private Integer boardHeight;
    private Integer boardWidth;
    private Integer amountOfCities;

    public int getBoardHeight() throws NoGameParameterException {

        try {
            return boardHeight;
        } catch (NullPointerException npe) {
            throw new NoGameParameterException();
        }

    }

    public boolean setBoardHeight(Integer boardHeight) throws BoardDimensionsException,
            GameParameterIsAlreadySetException {

        loger.addToLog("GameRules.setBoardHeight(" + boardHeight + ") {");
        if (boardHeight < minBoardHeight || boardHeight > maxBoardHeight) {
            loger.addToLog("   boardHeight = " + boardHeight + ", minBoardHeight = " + minBoardHeight +
                    ", maxBoardHeight = " + maxBoardHeight,
                    "   throw new BoardDimensionsException");
            loger.print();
            throw new BoardDimensionsException();
        } else if (this.boardHeight != null) {
            loger.addToLog("   boardHeight = " + this.boardHeight, "   throw new GameParameterIsAlreadySetException");
            loger.print();
            throw new GameParameterIsAlreadySetException();
        }
        this.boardHeight = boardHeight;
        loger.addToLog("   return true;", "}");
        loger.print();

        return true;
    }

    public int getBoardWidth() throws NoGameParameterException {

        try {
            return boardWidth;
        } catch (NullPointerException npe) {
            throw new NoGameParameterException();
        }
    }

    public boolean setBoardWidth(int boardWidth) throws BoardDimensionsException, GameParameterIsAlreadySetException {

        loger.addToLog("GameRules.setBoardWidth(" + boardWidth + ") {");
        if (boardWidth < minBoardWidth || boardWidth > maxBoardWidth) {
            loger.addToLog("   throw new BoardDimensionsException();", "}");
            loger.print();
            throw new BoardDimensionsException();
        } else if (this.boardWidth != null) {
            throw new GameParameterIsAlreadySetException();
        }
        this.boardWidth = boardWidth;
        loger.addToLog(" return true;", "}");
        loger.print();

        return true;
    }

    public int getAmountOfCities() throws NoGameParameterException {

        try {
            return amountOfCities;
        } catch (NullPointerException npe) {
            throw new NoGameParameterException();
        }

    }

    public boolean setAmountOfCities(int amountOfCities) throws AmountOfCitiesException, GameParameterIsAlreadySetException {

        int maxAmountOfCities = getMaxAmountOfCities();
        int minAmountOfCities = getMinAmountOfCities();
        loger.addToLog("GameRules.setAmountOfCities(" + amountOfCities + ") {",
                "   maxAmountOfCities = " + maxAmountOfCities, "   minAmountOfCities = " + minAmountOfCities);
        if (amountOfCities > maxAmountOfCities) {
            loger.addToLog("   amountOfCities is to big!", "}");
            loger.print();
            throw new AmountOfCitiesException();
        } else if (amountOfCities < minAmountOfCities) {
            loger.addToLog("   amountOFCities is to low!", "}");
            loger.print();
            throw new AmountOfCitiesException();
        } else if (this.amountOfCities != null) {
            throw new GameParameterIsAlreadySetException();
        }
        loger.addToLog("   return true;", "}");
        loger.print();
        this.amountOfCities = amountOfCities;

        return true;
    }

    public int getMinBoardHeight() {
        return minBoardHeight;
    }

    public int getMaxBoardHeight() {
        return maxBoardHeight;
    }

    public int getMinBoardWidth() {
        return minBoardWidth;
    }

    public int getMaxBoardWidth() {
        return maxBoardWidth;
    }

    public int getMinProcentRatioOfCitiesToTheNumberOfFields() {
        return minProcentRatioOfCitiesToTheNumberOfFields;
    }

    public int getMaxProcentRatioOfCitiesToTheNumberOfFields() {
        return maxProcentRatioOfCitiesToTheNumberOfFields;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String[] getRouteColors() {
        return routeColors;
    }

    public String[] getCardColors() {
        return cardColors;
    }

    public int getMinAmountOfCities() {

        int minAmountOfCities = boardHeight + boardWidth / 100 * minProcentRatioOfCitiesToTheNumberOfFields;
        loger.addToLog("GameRules.getMinAmountOfCities() {",
                "   boardHeight = " + boardHeight,
                "   boardWidth = " + boardWidth,
                "   boardSize = " + boardWidth * boardHeight,
                "   minProcentRtioOfCitiesToTheNumberOfFields = " + getMinProcentRatioOfCitiesToTheNumberOfFields(),
                "   minAmountOFCities = " + minAmountOfCities,
                "   return minAmountOfCities;", "}");
        loger.print();
        return minAmountOfCities;
    }

    public int getMaxAmountOfCities() {
        return boardHeight * boardWidth / 100 * maxProcentRatioOfCitiesToTheNumberOfFields;
    }
}
