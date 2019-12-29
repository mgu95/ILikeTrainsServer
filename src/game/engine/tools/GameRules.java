package game.engine.tools;

public class GameRules {

    private final int minBoardHeight = 10;
    private final int maxBoardHeight = 20;
    private final int minBoardWidth = 20;
    private final int maxBoardWidth = 30;
    private final int minProcentRatioOfCitiesToTheNumberOfFields = 10;
    private final int maxProcentRatioOfCitiesToTheNumberOfFields = 20;
    private final int minPlayers = 1;
    private final int maxPlayers = 5;
    private final String[] routeColors = new String[]{"Red", "Yellow", "Blue", "Orange", "Pink", "Grey"};
    private final String[] cardColors = new String[]{"Red", "Yellow", "Blue", "Orange", "Pink", "Rainbow"};

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
}
