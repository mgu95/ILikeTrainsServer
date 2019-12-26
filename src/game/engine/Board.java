package game.engine;

import game.engine.tools.GameRules;
import game.engine.tools.exceptions.NoGameParameterException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Board {

    private int width;
    private int height;
    private Field[][] fields;
    private Set<City> cities;
    private Set<Route> routes;
    private Set<Connection> connections;

    public Board(GameRules gameRules) throws NoGameParameterException {

        this.width = gameRules.getBoardWidth();
        this.height = gameRules.getBoardHeight();
        fields = new Field[width][height];
        generateCities(gameRules.getAmountOfCities());
    }

    private void placeCity() {

        Iterator<City> iterator = cities.iterator();
        while (iterator.hasNext()) {
            int randomWidth = new Random().nextInt(width);
            int randomHeight = new Random().nextInt(height);
            if (fields[randomWidth][randomHeight] == null) {
                if (!hasNeighbor(randomWidth, randomHeight)) {
                    fields[randomWidth][randomHeight] = iterator.next();
                }
            }
        }
    }

    private boolean hasNeighbor(int randomWidth, int randomHeight) {

        for (int i = randomWidth - 1; i < randomWidth + 2; i++) {
            for (int j = randomHeight - 1; j < randomHeight + 2; j++) {
                try {
                    if (fields[i][j] == null) {
                    } else if (fields[i][j].getClass() == City.class) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException aioobe) {}
            }
        }

        return false;
    }

    private void generateCities(int amount) {

        String[] cityNames = new String[]{"Tirana", "Andora", "Wiedeń", "Bruksela", "Mińsk", "Sarajewo", "Sofia",
                "Zagrzeb", "Kopenhaga", "Tallin", "Helsinki", "Paryż", "Ateny", "Madryt", "Amsterdam", "Dublin",
                "Rejkiawik", "Astana", "Moskwa", "Bukareszt", "San Marino", "Bratysława", "Lublana", "Berno",
                "Sztokholm", "Ankara", "Kijów", "Watykan", "Budapeszt", "Londyn", "Rzym"};
        cities = new HashSet<>();
        while (cities.size() < amount) {
            //cities.add(new City(generateFieldId()));
        }
    }

    public long generateFieldId(int fieldHeight, int fieldWidth) {
        return (width * (fieldHeight - 1)) + (width - (width - fieldWidth));
    }

    public void printBoard() {

        StringBuilder board = new StringBuilder("  ");
        StringBuilder legend = new StringBuilder("Legenda:\n");
        for (int i = 1; i < height + 1; i++) {
            board.append(" " + (char)(i + 64));
        }
        for (int i = 0; i < width; i++) {
            board.append("\n " + (i + 1));
            for (int j = 0; j < height; j++) {
                if (fields[i][j] == null) {
                    board.append("  ");
                } else if (fields[i][j].getClass() == City.class) {
                    board.append(" #");
                    legend.append((char)(j + 65) + "" + (i + 1) + " "
                            + fields[i][j].toString().substring(11, fields[i][j].toString().length() - 2) + "\n");
                }
//                else if (fields[i][j].getClass() == Route.class) {
//                    System.out.println("JEST  TRASA");
//                    board.append(" " + fields[i][j].toString());
//                }
            }
        }

        System.out.println(board.toString());
        System.out.println();
        System.out.println(legend.toString());
    }
}
