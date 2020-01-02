package game.engine;

import game.engine.tools.GameRules;

import java.util.*;

public class Board {

    //zmienne potrzebne do nowego boarda:
    private long id;
    private Field[][] fields;
    private Player[] players;

    public static void main(String[] args) {
        Board board = new Board(1, 20, 26, 5);
        board.getDefaultBoard();

        board.print();
    }

    public Board(long id, int height, int width, int numberOfPlayers) {
        this.id = id;
        this.fields = new Field[width][height];
        this.players = new Player[numberOfPlayers];
        long fieldId = 1;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                fields[i][j] = new Field(fieldId, i, j);
                fieldId++;
            }
        }
    }

    public Board getDefaultBoard() {
        Board board = new Board(1, 20, 30, 5);
        setCity(fields[0][0], 1, "Tirana");
        setCity(fields[6][0], 2, "Andora");
        setCity(fields[12][0], 3, "Wiedeń");
        setCity(fields[16][0], 4, "Bruksela");
        setCity(fields[21][1], 5, "Mińsk");
        setCity(fields[24][1], 6, "Sarajewo");
        setCity(fields[12][2], 7, "Sofia");
        setCity(fields[0][3], 8, "Zagrzeb");
        setCity(fields[19][3], 9, "Kopenhaga");
        setCity(fields[4][4], 10, "Helsinki");
        setCity(fields[10][4], 11, "Paryż");
        setCity(fields[24][4], 12, "Ateny");
        setCity(fields[16][6], 13, "Madryt");
        setCity(fields[22][6], 14, "Amsterdam");
        setCity(fields[4][7], 15, "Dublin");
        setCity(fields[10][9], 16, "Rejkiawik");
        setCity(fields[16][9], 17, "Astana");
        setCity(fields[25][9], 18, "Moskwa");
        setCity(fields[0][12], 19, "Bukareszt");
        setCity(fields[13][12], 20, "San Marino");
        setCity(fields[19][12], 21, "Bratysława");
        setCity(fields[22][12], 22, "Lublana");
        setCity(fields[10][13], 23, "Berno");
        setCity(fields[0][15], 24, "Sztokholm");
        setCity(fields[4][15], 25, "Ankara");
        setCity(fields[10][15], 26, "Kijów");
        setCity(fields[13][15], 27, "Watykan");
        setCity(fields[16][15], 28, "Budapeszt");
        setCity(fields[22][15], 29, "Londyn");
        setCity(fields[25][15], 30, "Rzym");
        setCity(fields[7][18], 31, "Wrocław");
        setCity(fields[13][18], 32, "Iława");
        setCity(fields[0][19], 33, "Olsztyn");
        setCity(fields[4][19], 34, "Poznań");
        setCity(fields[16][19], 35, "Warszawa");
        setCity(fields[19][19], 36, "Kraków");
        setCity(fields[22][19], 37, "Rzeszów");

        setRoute(1, "ORANGE", 1, 2);
        //tutaj zolta trasa
        setRoute(2, "RED", 1, 8);








        return board;
    }

    private Field getField(long fieldId) {
        for(Field[] fields : getFields()) {
            for (Field field : fields) {
                if (field.getFieldId() == fieldId) {
                    return field;
                }
            }
        }
        throw new NullPointerException("There is no field with id " + fieldId + "!");
    }

    private City getCity(long cityId) {
        for(Field[] fields : fields) {
            for (Field field : fields) {
                if (field.getClass() == City.class) {
                    City c = (City) field;
                    if (c.getCityId() == cityId) {
                        return c;
                    }
                }
            }
        }
        throw new NullPointerException("There is no city with id " + cityId + "!");
    }

    private void setCity(Field field, long cityId, String name) {
        fields[field.getXPosition()][field.getYPosition()]
                = new City(fields[field.getXPosition()][field.getYPosition()], cityId, name);
    }

    private Route getRoute(long routeId) {
        for(Field[] fields : fields) {
            for (Field field : fields) {
                if (field.getClass() == Route.class) {
                    Route r = (Route) field;
                    if (r.getRouteId() == routeId) {
                        return r;
                    }
                }
            }
        }
        throw new NullPointerException("There is no route with id " + routeId + "!");
    }

    private void setRoute(long routeId, String color, long fromCityId, long toCityId) {
        City cityA = getCity(fromCityId);
        City cityB = getCity(toCityId);
        cityA.addRouteId(routeId);
        cityB.addRouteId(routeId);
        if (cityA.getYPosition() == cityB.getYPosition()) {
            for (int i = cityA.getXPosition() + 1; i < cityB.getXPosition(); i++) {
                fields[i][cityA.getYPosition()] = new Route(fields[i][cityA.getYPosition()], routeId, color);
            }
        } else if (cityA.getXPosition() == cityB.getXPosition()) {
            for (int i = cityA.getYPosition() + 1; i < cityB.getYPosition(); i++) {
                fields[cityA.getXPosition()][i] = new Route(fields[cityA.getXPosition()][i], routeId, color);
            }
        } else {
            // implementacja szukania miasta na ukos
        }
        fields[cityA.getXPosition()][cityA.getYPosition()] = cityA;
        fields[cityB.getXPosition()][cityB.getYPosition()] = cityB;
    }

    public void print() {
        StringBuilder board = new StringBuilder("  ");
        StringBuilder cityLegend = new StringBuilder("Miasta:\n");
        StringBuilder routeLegend = new StringBuilder("Trasy:\n");
        Field[][] f = this.fields;
        for (int i = 1; i < f.length + 1; i++) {
            board.append((char)(i + 64) + " ");
        }
        Set<Route> allRoutes = new HashSet<>();
        for (int i = 0; i < f[0].length; i++) {
            if (i < 10) {
                board.append("\n" + (i + 1) + " ");
            } else {
                board.append("\n" + (i + 1));
            }

            for (int j = 0; j < f.length; j++) {
                if (f[j][i].getClass() == Field.class) {
                    board.append("  ");
                } else if (f[j][i].getClass() == City.class) {
                    board.append("# ");
                    City tmpCity = (City) f[j][i];
                    long[] routes = tmpCity.getRoutesId();
                    cityLegend.append((char)(j + 65) + "" + (i + 1) + " " + tmpCity.getName() + " - ID = "
                            + tmpCity.getCityId() + ", połączenia = ");
                    for (long routeId : routes) {
                        cityLegend.append(routeId + " (" + getRoute(routeId).getColor() + "), ");
                    }
                    cityLegend.append("właściciel = ");
                    if (tmpCity.getOwner() != null) {
                        cityLegend.append(tmpCity.getOwner());
                    } else {
                        cityLegend.append("brak");
                    }
                    cityLegend.append("\n");
                } else if (f[j][i].getClass() == Route.class) {
                    Route tmpRoute = (Route) f[j][i];
                    board.append(tmpRoute.getColor().substring(0, 1) + " ");
                    allRoutes.add(tmpRoute);
                }
            }
        }
        long highestRouteId = 0;
        for (Route route : allRoutes) {
            if (route.getRouteId() > highestRouteId) {
                highestRouteId = route.getRouteId();
            }
        }
        for (int i = 1; i < highestRouteId + 1; i++) {
            Set<Route> rSet = new HashSet<>();
            for (Route route : allRoutes) {
                if (route.getRouteId() == i) {
                    rSet.add(route);
                }
            }
            routeLegend.append(i + " (" + rSet.iterator().next().getColor() + ") = ");
            for (Route route : rSet) {
                routeLegend.append((char)(route.getYPosition() + 65) + "" + (route.getXPosition() + 1) + " ");
            }
            routeLegend.append("\n");
        }
        System.out.println(board.toString() + "\n\n" + cityLegend.toString() + "\n" + routeLegend.toString());
    }

    //zmienne potrzebne do starego boarda:
    private int width;
    private int height;
    private Set<City> cities = new HashSet<>();

//    public static void main(String[] args) {
//        Board board = new Board(1L, "testBoard", 1);
//        board.generateFields();
//        board.generateCities(20);
//        board.connectCitiesHorizontally();
//
//        board.printBoard();
//
//
////        for(Field[] fields : board.fields) {
////            for (Field fff : fields) {
////                System.out.println(field.toString());
////            }
////        }
//    }

    private void connectCitiesHorizontally() {
        long rId = 1l;
        String rColor = GameRules.getRouteColors()[new Random().nextInt(GameRules.getRouteColors().length)];
        for (int i = 0; i < height; i++) {
            Set<City> citiesAtTheSameHeight = new HashSet<>();
            for (City city : cities) {
                if (city.getYPosition() == i) {
                    citiesAtTheSameHeight.add(city);
                }
            }
            if (citiesAtTheSameHeight.size() == 2) {
                City firstCityOnLeft = citiesAtTheSameHeight.iterator().next();
                for (City city : citiesAtTheSameHeight) {
                    if (city.getXPosition() < firstCityOnLeft.getXPosition()) {
                        firstCityOnLeft = city;
                    }
                }
                City firstCityOnRight = firstCityOnLeft;
                for (City city : citiesAtTheSameHeight) {
                    if (!city.equals(firstCityOnLeft)) {
                        if (city.getXPosition() > firstCityOnRight.getXPosition()) {
                            firstCityOnRight = city;
                        }
                    }
                }
                City c = (City) fields[firstCityOnLeft.getXPosition()][firstCityOnLeft.getYPosition()];
                c.addRouteId(rId);
                fields[firstCityOnLeft.getXPosition()][firstCityOnLeft.getYPosition()] = c;
                City d = (City) fields[firstCityOnRight.getXPosition()][firstCityOnRight.getYPosition()];
                d.addRouteId(rId);
                fields[firstCityOnRight.getXPosition()][firstCityOnRight.getYPosition()] = d;
                for (int j = firstCityOnLeft.getXPosition() + 1; j < firstCityOnRight.getXPosition(); j++) {
                    fields[j][i] = new Route(fields[j][i], rId, rColor);
                }
                rId++;
                rColor = GameRules.getRouteColors()[new Random().nextInt(GameRules.getRouteColors().length)];
            }
            //wiecej miast w rzedzie
            if (citiesAtTheSameHeight.size() > 2) {
                //zmień set w tablice
                City[] citiesArr = new City[citiesAtTheSameHeight.size()];
                Iterator<City> iterator = citiesAtTheSameHeight.iterator();
                for (int j = 0; j < citiesArr.length; j++) {
                    City c = iterator.next();
                    citiesArr[j] = c;
                }
                //posortuj tablice
                City tempCity;
                for (int j = 0; j < citiesArr.length; j++) {
                    for (int k = 1; k < (citiesArr.length - j); k++){
                        if (citiesArr[k-1].getXPosition() > citiesArr[k].getXPosition()) {
                            tempCity = citiesArr[k-1];
                            citiesArr[k-1] = citiesArr[k];
                            citiesArr[k] = tempCity;
                        }
                    }
                }
                for (int j = 0; j < citiesArr.length - 1; j++) {
                    City c = citiesArr[j];
                    c.addRouteId(rId);
                    fields[c.getXPosition()][c.getYPosition()] = c;
                    City d = citiesArr[j + 1];
                    d.addRouteId(rId);
                    fields[d.getXPosition()][d.getYPosition()] = d;
                    for (int k = c.getXPosition() + 1; k < d.getXPosition(); k++) {
                        fields[k][c.getYPosition()] = new Route(fields[k][c.getYPosition()], rId, rColor);
                    }
                    rId++;
                    rColor = GameRules.getRouteColors()[new Random().nextInt(GameRules.getRouteColors().length)];
                }
            }
            citiesAtTheSameHeight.clear();
        }
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
                    cities.add((City) fields[randomWidth][randomHeight]);
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
        StringBuilder cityLegend = new StringBuilder("Miasta:\n");
        StringBuilder routeLegend = new StringBuilder("Trasy:\n");
        for (int i = 1; i < getWidth() + 1; i++) {
            board.append(" " + (char)(i + 64));
        }
        for (int i = 0; i < getHeight(); i++) {
            board.append("\n " + (i + 1));
            for (int j = 0; j < getWidth(); j++) {
                if (fields[j][i].getClass() == Field.class) {
                    board.append("  ");
                } else if (fields[j][i].getClass() == City.class) {
                    City tmp = (City) fields[j][i];
                    board.append("# "/* + tmp.getName().substring(0, 1)*/);
                    cityLegend.append((char)(j + 65) + "" + (i + 1) + " " + fields[j][i].toString() + "\n");
                } else if (fields[j][i].getClass() == Route.class) {
                    Route tmp = (Route) fields[j][i];
                    board.append("-" + tmp.getColor().substring(0, 1));
                    routeLegend.append((char)(j + 65) + "" + (i + 1) + " " + fields[j][i].toString() + "\n");
                }
//                else if (fields[i][j].getClass() == Route.class) {
//                    System.out.println("JEST  TRASA");
//                    board.append(" " + fields[i][j].toString());
//                }
            }
        }





        System.out.println(board.toString());
        System.out.println();
        System.out.println(cityLegend.toString());
        System.out.println();
        System.out.println(routeLegend.toString());
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

    public void setFields(Field[][] fields) {
        this.fields = fields;
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
                ", wielkość planszy = " + width +
                " * " + height +
                ", ilość miast = ERROR" +
                ", ilość graczy = " + emptyPlayers + "/" + players.length +
                " ]";
    }
}
