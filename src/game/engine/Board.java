package game.engine;

import game.engine.tools.GameRules;

import java.util.*;

public class Board {

    private long id;
    private Field[][] fields;
    private Player[] players;

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

    public Board(long id) {
        this.id = id;
        this.fields = new Field[26][20];
        this.players = new Player[5];
        long fieldId = 1;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 20; j++) {
                fields[i][j] = new Field(fieldId, i, j);
                fieldId++;
            }
        }

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
        setRoute(2, "YELLOW", 1, 10);
        setRoute(3, "RED", 1, 8);
        setRoute(4, "BLUE", 2, 3);
        setRoute(5, "ORANGE", 2, 11);
        setRoute(6, "GREY", 3, 4);
        setRoute(7, "PINK", 3, 7);
        setRoute(8, "RED", 4, 9);
        setRoute(9, "GREY", 5, 6);
        setRoute(10, "BLUE", 5, 12);
        setRoute(11, "PINK", 5, 9);
        setRoute(12, "RED", 6, 12);
        setRoute(13, "ORANGE" ,7, 13);
        setRoute(14, "YELLOW", 7, 11);
        setRoute(15, "RED", 8, 15);
        setRoute(16, "PINK", 8, 19);
        setRoute(17, "YELLOW", 9, 14);
        setRoute(18, "GREY", 9, 21);
        setRoute(19, "YELLOW", 9, 13);
        setRoute(20, "BLUE", 10, 11);
        setRoute(21, "YELLOW", 10, 15);
        setRoute(22, "PINK", 11,16);
        setRoute(23, "GREY", 12, 14);
        setRoute(24, "RED", 13, 17);
        setRoute(25, "RED", 14, 18);
        setRoute(26, "PINK", 14, 22);
        setRoute(27, "GREY", 15,23);
        setRoute(28, "PINK", 15, 25);
        setRoute(29, "GREY", 15, 19);
        setRoute(30, "GREY", 16, 20);
        setRoute(31, "BLUE", 16, 23);
        setRoute(32, "ORANGE", 17, 21);
        setRoute(33, "BLUE", 17, 20);
        setRoute(34, "BLUE", 18, 30);
        setRoute(35, "ORANGE", 18, 22);
        setRoute(36, "RED", 19, 24);
        setRoute(37, "YELLOW", 20, 27);
        setRoute(38, "BLUE", 21, 22);
        setRoute(39, "RED", 21, 36);
        setRoute(40, "PINK", 21, 28);
        setRoute(41, "YELLOW", 22, 29);
        setRoute(42, "RED", 23, 26);
        setRoute(43, "YELLOW", 24, 25);
        setRoute(44, "PINK", 24, 33);
        setRoute(45, "BLUE", 25, 26);
        setRoute(46, "ORANGE", 25, 31);
        setRoute(47, "GREY", 25, 34);
        setRoute(48, "BLUE", 25, 33);
        setRoute(49, "ORANGE", 26, 27);
        setRoute(50, "GREY", 26, 32);
        setRoute(51, "YELLOW", 26, 31);
        setRoute(52, "RED", 27, 32);
        setRoute(53, "BLUE", 28, 35);
        setRoute(54, "BLUE", 29, 30);
        setRoute(55, "ORANGE", 29, 37);
        setRoute(56, "PINK", 31, 32);
        setRoute(57, "ORANGE", 33, 34);
        setRoute(58, "ORANGE", 35, 36);
        setRoute(59, "PINK", 36, 37);
        setRoute(60, "YELLOW", 28, 32);
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

    private Set<City> getAllCities() {
        Set<City> set = new HashSet<>();
        for(Field[] fields : fields) {
            for (Field field : fields) {
                if (field.getClass() == City.class) {
                    set.add((City) field);
                }
            }
        }
        return set;
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

    private Set<Route> getAllRoutes() {
        Set<Route> set = new HashSet<>();
        for(Field[] fields : fields) {
            for (Field field : fields) {
                if (field.getClass() == Route.class) {
                    set.add((Route) field);
                }
            }
        }
        return set;
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
            City highestCity;
            if (cityA.getYPosition() < cityB.getYPosition()) { highestCity = cityA; }
            else { highestCity = cityB; }
            City cityOnLeft;
            if (cityA.getXPosition() < cityB.getXPosition()) { cityOnLeft = cityA; }
            else { cityOnLeft = cityB; }
            if (highestCity.equals(cityA) && cityOnLeft.equals(cityA)) {
                int y = cityB.getYPosition() - cityA.getYPosition();
                for (int i = 1; i < y; i++) {
                    fields[cityA.getXPosition() + i][cityA.getYPosition() + i]
                           = new Route(fields[cityA.getXPosition() + i][cityA.getYPosition() + i], routeId, color);
                }
            } else if (highestCity.equals(cityA) && cityOnLeft.equals(cityB)) {
                int y = cityB.getYPosition() - cityA.getYPosition();
                for (int i = 1; i < y; i++) {
                    fields[cityA.getXPosition() - i][cityA.getYPosition() + i]
                            = new Route(fields[cityA.getXPosition() - i][cityA.getYPosition() + i], routeId, color);
                }
            }

        }
        fields[cityA.getXPosition()][cityA.getYPosition()] = cityA;
        fields[cityB.getXPosition()][cityB.getYPosition()] = cityB;
    }

    public void print() {
        StringBuilder board = new StringBuilder("   ");
        StringBuilder cityLegend = new StringBuilder("Miasta:\n");
        StringBuilder routeLegend = new StringBuilder("Trasy:\n");
        Field[][] f = this.fields;
        for (int i = 1; i < f.length + 1; i++) {
            board.append((char)(i + 64) + " ");
        }
        Set<Route> allRoutes = new HashSet<>();
        for (int i = 0; i < f[0].length; i++) {
            if (i < 9) {
                board.append("\n" + (i + 1) + "  ");
            } else {
                board.append("\n" + (i + 1) + " ");
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
            routeLegend.append(i + " (" + getRoute(i).getColor() + ") = ");
            Set<City> allCitiesOnBoard = getAllCities();
            for (City city : allCitiesOnBoard) {
                long[] routesId = city.getRoutesId();
                for (long l : routesId) {
                    if (l == i) {
                        routeLegend.append(city.getName() + " <-> ");
                    }
                }
            }
            String tmp = routeLegend.substring(0, routeLegend.length() - 5);
            routeLegend.setLength(0);
            routeLegend.append(tmp);
            routeLegend.append("\n");
        }
        System.out.println(board.toString() + "\n\n" + cityLegend.toString() + "\n" + routeLegend.toString());
    }


    // OLD BOARD
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
