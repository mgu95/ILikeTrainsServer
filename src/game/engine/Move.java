package game.engine;

import game.engine.tools.Deck;

import java.util.Set;

public class Move {

    public static void main(String[] args) {
        Move move = new Move();
        Board board = new Board(1);
        Player testPlayer = new Player("testPlayer");
        testPlayer.setDeck(new Deck(1, 5));
        board.setPlayers(new Player[]{testPlayer, new Player("player1"), new Player("player2"),
                new Player("player3"), new Player("player4")});
        board.print();
    }

    public Board buyRoute(String login, Board board, long routeId) {
        Set<Route> routes = board.getRoute(routeId);
        Player player = getPlayer(login, board);
        if (routes.iterator().next().getOwner() == null) {
            if (player.getDeck().getAmountCards(routes.iterator().next().getColor()) >= routes.size()) {
                for (Route route : routes) {
                    route.setOwner(login);
                    board.getDeck().returnCard(player.getDeck().takeCard(routes.iterator().next().getColor()));
                }
            } else if (player.getDeck().getAmountCards(routes.iterator().next().getColor()) == 0
                    && player.getDeck().getAmountCards("RAINBOW") >= routes.size()) {
                for (Route route : routes) {
                    route.setOwner(login);
                    board.getDeck().returnCard(player.getDeck().takeCard("RAINBOW"));
                }
            } else if (player.getDeck().getAmountCards(routes.iterator().next().getColor())
                    + player.getDeck().getAmountCards("RAINBOW") >= routes.size()) {
                for (Route route : routes) {
                    route.setOwner(login);
                    if (player.getDeck().getAmountCards(routes.iterator().next().getColor()) > 0) {
                        board.getDeck().returnCard(player.getDeck().takeCard(routes.iterator().next().getColor()));
                    } else {
                        board.getDeck().returnCard(player.getDeck().takeCard("RAINBOW"));
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
