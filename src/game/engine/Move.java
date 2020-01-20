package game.engine;

import java.util.Set;

public class Move {

    public Board buyRoute(String login, Board board, long routeId) {
        Set<Route> routes = board.getRoute(routeId);
        Player player = getPlayer(login, board);
        if (routes.iterator().next().getOwner() == null) {
            if (player.getDeck().getAmountCards(routes.iterator().next().getColor()) >= routes.size()) {
                for (Route route : routes) {
                    route.setOwner(login);
                    board.getDeck().addCards(player.getDeck().getCard(routes.iterator().next().getColor()));
                }
            } else if (player.getDeck().getAmountCards(routes.iterator().next().getColor()) == 0
                    && player.getDeck().getAmountCards("RAINBOW") >= routes.size()) {
                for (Route route : routes) {
                    route.setOwner(login);
                    board.getDeck().addCards(player.getDeck().getCard("RAINBOW"));
                }
            } else if (player.getDeck().getAmountCards(routes.iterator().next().getColor())
                    + player.getDeck().getAmountCards("RAINBOW") >= routes.size()) {
                for (Route route : routes) {
                    route.setOwner(login);
                    if (player.getDeck().getAmountCards(routes.iterator().next().getColor()) > 0) {
                        board.getDeck().addCards(player.getDeck().getCard(routes.iterator().next().getColor()));
                    } else {
                        board.getDeck().addCards(player.getDeck().getCard("RAINBOW"));
                    }
                }
            } else {
                throw new IllegalArgumentException("Player has too few cards!");
            }
            updatePlayers(player, board);
            board.setRoute(routes);
        } else {
            throw new IllegalArgumentException("Route already has an owner!");
        }
        return board;
    }

    private void updatePlayers (Player player, Board board) {
        Player[] players = board.getPlayers();
        for (Player pl : players) {
            if (pl.getLogin().equals(player.getLogin())) {
                pl = player;
            }
        }
        board.setPlayers(players);
    }

    private Player getPlayer(String login, Board board) {
        Player[] players = board.getPlayers();
        for (Player player : players) {
            if (player.getLogin().equals(login)) {
                return player;
            }
        }
        throw new IllegalArgumentException("There is no user with login " + login + "!");
    }
}
