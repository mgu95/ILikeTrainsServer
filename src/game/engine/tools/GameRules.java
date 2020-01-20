package game.engine.tools;

public enum GameRules {

    BOARD_HEIGHT(10, 20),
    BOARD_WIDTH(20, 30),
    PROCENT_OF_CITIES(10, 20),
    NUMBER_OF_PLAYERS(1, 5);

    private int min;
    private int max;
    private final static String[] cardColors = new String[]{"PINK", "BLACK", "BLUE", "YELLOW", "RED", "ORANGE",
            "WHITE", "GREEN", "RAINBOW"};
    private final static String[] playerColors = new String[]{"RED", "YELLOW", "BLUE", "GREEN", "BLACK"};

    GameRules(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public static String[] getPlayerColors() {
        return playerColors;
    }

    public static String[] getCardColors() {
        return cardColors;
    }
}
