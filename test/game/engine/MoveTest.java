package game.engine;

import game.engine.tools.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    private Move move;
    private Board board;
    private Player testPlayer;

    // Deck balance
    // [0] = Red cards
    // [1] = Yellow cards
    // [2] = Blue cards
    // [3] = Orange cards
    // [4] = Pink cards
    // [5] = Rainbow cards

    int[] getPlayerDeckBalance() {
        return new int[]{testPlayer.getDeck().getAmountCards("RED"),
                testPlayer.getDeck().getAmountCards("YELLOW"),
                testPlayer.getDeck().getAmountCards("BLUE"),
                testPlayer.getDeck().getAmountCards("ORANGE"),
                testPlayer.getDeck().getAmountCards("PINK"),
                testPlayer.getDeck().getAmountCards("RAINBOW")};
    }

    @BeforeEach
    void setUp() {
        move = new Move();
        board = new Board(1);
        testPlayer = new Player("testPlayer");
        testPlayer.setDeck(new Deck(1, 5));
        board.setPlayers(new Player[]{testPlayer, new Player("player1"), new Player("player2"),
                new Player("player3"), new Player("player4")});
    }

    @Test
    void whenPlayerBuyRouteCardShouldBackToBoardDeck() {
        board = move.buyRoute("testPlayer", board, 2);
        assertEquals(23, board.getDeck().getAmountCards("YELLOW"));
    }

    @Test
    void playerShouldBuyRouteWithNormalCard() {
        board = move.buyRoute("testPlayer", board, 2);
        assertEquals(2, getPlayerDeckBalance()[1]);
    }

    @Test
    void playerShouldBuyRouteWithNormalCardAndRainbowCard() {
        board = move.buyRoute("testPlayer", board, 39);
        int[] playerBalance = getPlayerDeckBalance();
        int[] redAndRainbowCardBalance = new int[]{playerBalance[0], playerBalance[5]};
        assertArrayEquals(new int[]{0, 0}, redAndRainbowCardBalance);
    }

    @Test
    void playerShouldBuyRouteWithOnlyRainbowCards() {
        testPlayer.setDeck(new Deck(5, 0));
        board.setPlayers(new Player[]{testPlayer, new Player("player1"), new Player("player2"),
                new Player("player3"), new Player("player4")});
        board = move.buyRoute("testPlayer", board, 54);
        assertEquals(3, getPlayerDeckBalance()[5]);
    }

    @Test
    void playerCantBuyRoute() {
        testPlayer.setDeck(new Deck(0, 0));
        board.setPlayers(new Player[]{testPlayer, new Player("player1"), new Player("player2"),
                new Player("player3"), new Player("player4")});
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> move.buyRoute("testPlayer", board, 2)
        );
        assertEquals("Player has too few cards!", exception.getMessage());
    }

    @Test
    void routeAlreadyHasAnOwner() {
        board = move.buyRoute("testPlayer", board, 2);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> move.buyRoute("testPlayer", board, 2)
        );
        assertEquals("Route already has an owner!", exception.getMessage());
    }
}