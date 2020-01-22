package game.engine.tools;

import game.engine.Board;
import game.engine.City;
import game.engine.Route;

import java.util.*;

public class Printer {

    String[][] strBoard;
    private Map<String, String> textColor = new HashMap<>();
    private Map<String, String> backgroundColor = new HashMap<>();

    public Printer() {
        textColor.put("RESET", "\u001B[0m");
        textColor.put("BLACK", "\u001B[30m");
        textColor.put("RED", "\u001B[31m");
        textColor.put("GREEN", "\u001B[32m");
        textColor.put("YELLOW","\u001B[33m");
        textColor.put("BLUE", "\u001B[34m");
        textColor.put("PINK", "\u001B[35m");
        textColor.put("CYAN", "\u001B[36m");
        textColor.put("WHITE", "\u001B[37m");
        textColor.put("ORANGE", "\u001B[36m");

        backgroundColor.put("RESET", "\u001B[48m");
        backgroundColor.put("BLACK", "\u001B[40m");
        backgroundColor.put("RED", "\u001B[41m");
        backgroundColor.put("GREEN", "\u001B[42m");
        backgroundColor.put("YELLOW", "\u001B[43m");
        backgroundColor.put("BLUE", "\u001B[44m");
        backgroundColor.put("PINK", "\u001B[45m");
        backgroundColor.put("CYAN", "\u001B[46m");
        backgroundColor.put("WHITE", "\u001B[47m");
        backgroundColor.put("ORANGE", "\u001B[46m");
    }

    public String toString(Board board) {
        strBoard = new String[board.getFields()[0].length][board.getFields().length];
        addCities(board);
        Map<Long, ArrayList<Route>> routesMap = putAllRoutesToMap(board);

        for (Long l = 1L; l < routesMap.size() + 1; l++) {
            Object[] array = routesMap.get(l).toArray();
            for (int i = 0; i < array.length; i++) {
                Route route = (Route) array[i];
                if (route.getColor() == null) {
                    Object[] color = textColor.keySet().toArray();
                    String rId = Long.toString(route.getRouteId());
                    if (rId.length() < 2) {
                        strBoard[route.getYPosition()][route.getXPosition()] =
                                textColor.get(color[new Random().nextInt(textColor.size())]) + "0" +
                                textColor.get(color[new Random().nextInt(textColor.size())]) + rId + " " +
                                textColor.get("RESET");;
                    } else {
                        strBoard[route.getYPosition()][route.getXPosition()] =
                                textColor.get(color[new Random().nextInt(textColor.size())]) + rId.substring(0, 1) +
                                textColor.get(color[new Random().nextInt(textColor.size())]) + rId.substring(1) + " " +
                                textColor.get("RESET");
                    }
                } else {
                    if (route.getRouteId() < 10) {
                        strBoard[route.getYPosition()][route.getXPosition()] = textColor.get(route.getColor()) +
                                route.getRouteId() + " " + textColor.get("RESET") + " ";
                    } else {
                        strBoard[route.getYPosition()][route.getXPosition()] = textColor.get(route.getColor()) +
                                route.getRouteId() + textColor.get("RESET") + " ";
                    }
                }
            }
        }

        StringBuilder boardString = new StringBuilder("   ");
        for (int i = 1; i < strBoard[0].length + 1; i++) {
            boardString.append((char)(i + 64) + "  ");
        }
        boardString.append("\n");
        for (int i = 0; i < strBoard.length; i++) {
            if (i < 9) {
                boardString.append((i + 1) + "  ");
            } else {
                boardString.append((i + 1) + " ");
            }
            for (int j = 0; j < strBoard[i].length; j++) {
                if (strBoard[i][j] == null) {
                    boardString.append("   ");
                } else {
                    boardString.append(strBoard[i][j]);
                }
            }
            boardString.append("\n");
        }
        boardString.append("\nUWAGA!\n" +
                "1. Ze względu na brak koloru pomarańczowego, trasy tego koloru są oznaczone kolorem " +
                "\u001B[36mJASNO NIEBIESKIM\u001B[0m!\n" +
                "2. Ze względu na motyw środowiska uruchomieniowego kolor czarny i biały mogą wyświetlać się " +
                "odwrotnie (białe trasy są wyświetlane jako czarne, a czarne jako białe)!\n");

        return boardString.toString();
    }

    private Map<Long, ArrayList<Route>> putAllRoutesToMap(Board board) {
        Map<Long, ArrayList<Route>> routesMap= new HashMap<>();
        for (Route route : board.getAllRoutes()) {
            ArrayList<Route> arrayList;
            if (!routesMap.containsKey(route.getRouteId())) {
                arrayList = new ArrayList<>();
            } else {
                arrayList = routesMap.get(route.getRouteId());
            }
            arrayList.add(route);
            routesMap.put(route.getRouteId(), arrayList);
        }

        return routesMap;
    }

    private void addCities(Board board) {
        for (City city : board.getAllCities()) {
            if (city.getCityId() < 10) {
                strBoard[city.getYPosition()][city.getXPosition()] =
                        backgroundColor.get("CYAN") + textColor.get("BLACK") + "#" + city.getCityId() + " " +
                        backgroundColor.get("RESET") + textColor.get("RESET");
            } else {
                strBoard[city.getYPosition()][city.getXPosition()] =
                        backgroundColor.get("CYAN") + textColor.get("BLACK") + "#" + city.getCityId() +
                                backgroundColor.get("RESET") + textColor.get("RESET");
            }
        }
    }
}
