package game.engine;

import game.engine.tools.GameRules;

import java.util.*;

public class Board {

    private GameRules gameRules = new GameRules();
    private long id;
    private String name;
    private int width = (gameRules.getMinBoardWidth() + gameRules.getMaxBoardWidth()) / 2;
    private int height = (gameRules.getMinBoardHeight() + gameRules.getMaxBoardHeight()) / 2;
    private Field[][] fields;
    private Player[] players;

    public static void main(String[] args) {
        Board board = new Board(1L, "testBoard", 1);
        board.printBoard();

    }

    public Board(long id, String name, int players) {
        this.id = id;
        this.name = name;
        this.players = new Player[players];
        generateFields();
        generateCities(20);

//        for(Field[] fields : fields) {
//            for (Field field : fields) {
//                System.out.println(field.toString());
//            }
//        }
    }

    private void generateCities(int amount) {
        String[] cityNames = new String[]{"Tirana", "Andora", "Wiedeń", "Bruksela", "Mińsk", "Sarajewo", "Sofia",
                "Zagrzeb", "Kopenhaga", "Tallin", "Helsinki", "Paryż", "Ateny", "Madryt", "Amsterdam", "Dublin",
                "Rejkiawik", "Astana", "Moskwa", "Bukareszt", "San Marino", "Bratysława", "Lublana", "Berno",
                "Sztokholm", "Ankara", "Kijów", "Watykan", "Budapeszt", "Londyn", "Rzym"};
        Set<String> set = new HashSet<>(Arrays. asList(cityNames));
        Iterator<String> iterator = set.iterator();

        for (long i = 0; i < amount; i++) {
            int randomWidth = new Random().nextInt(getWidth());
            int randomHeight = new Random().nextInt(getHeight());
            if (fields[randomWidth][randomHeight].getClass() == Field.class) {
                if (!hasNeighbor(randomWidth, randomHeight)) {
                    fields[randomWidth][randomHeight] =
                            new City(fields[randomWidth][randomHeight], i + 1, iterator.next());
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

    public void printBoard() {

        StringBuilder board = new StringBuilder("  ");
        StringBuilder legend = new StringBuilder("Legenda:\n");
        for (int i = 1; i < getWidth() + 1; i++) {
            board.append(" " + (char)(i + 64));
        }
        for (int i = 0; i < getHeight(); i++) {
            board.append("\n " + (i + 1));
            for (int j = 0; j < getWidth(); j++) {
                if (fields[j][i].getClass() == Field.class) {
                    board.append("  ");
                } else if (fields[j][i].getClass() == City.class) {
                    board.append(" #");
                    legend.append((char)(j + 65) + "" + (i + 1) + " "
                            + fields[j][i].toString() + "\n");
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        generateFields();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        generateFields();
    }

    public Field[][] getFields() {
        return fields;
    }

    private void generateFields() {
        fields = new Field[getWidth()][getHeight()];
        long id = 1L;
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                fields[i][j] = new Field(id, i, j);
                id++;
            }
        }
    }

    public String getInfo() {

        int emptyPlayers = 0;
        for (Player player : players) {
            if (player == null) {
                emptyPlayers++;
            }
        }
        return "[ id = " + id +
                ", nazwa stołu = " + name +
                ", wielkość planszy = " + width +
                " * " + height +
                ", ilość miast = ERROR" +
                ", ilość graczy = " + emptyPlayers + "/" + players.length +
                " ]";
    }
}
