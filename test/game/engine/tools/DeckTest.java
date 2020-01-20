package game.engine.tools;

import game.engine.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(10, 11, 12, 13, 14, 15,
                16, 17, 18);
    }

    @ParameterizedTest
    @CsvSource({"PINK,10", "BLACK,11", "BLUE,12", "YELLOW,13", "RED,14", "ORANGE,15", "WHITE,16", "GREEN,17",
            "RAINBOW,18"})
    void getAmountOfCards(String color, String expected) {
        assertEquals(Integer.parseInt(expected), deck.getAmountCards(color));
    }

    @ParameterizedTest
    @ValueSource(strings = {"PINK", "BLACK", "BLUE", "YELLOW", "RED", "ORANGE", "WHITE", "GREEN", "RAINBOW"})
    void afterTakeACardSetShouldBeReduced(String color) {
        int expected = deck.getAmountCards(color);
        expected--;
        deck.getCard(color);
        int actual = deck.getAmountCards(color);
        assertEquals(expected, actual);
    }

    @RepeatedTest(10)
    void randomCardsAlwaysShouldBeRandom() {
        int amountOfRandomCards = 10;
        Card[] randomCards = deck.getRandomCards(amountOfRandomCards);
        String[] colors = new String[]{"PINK", "BLACK", "BLUE", "YELLOW", "RED", "ORANGE", "WHITE", "GREEN", "RAINBOW"};
        int sameColor = 0;
        boolean allCardsHaveTheSameColor = false;
        for (String color : colors) {
            for (Card card : randomCards) {
                if (card.getColor().equals(color)) {
                    sameColor++;
                }
            }
            if (sameColor == amountOfRandomCards) {
                allCardsHaveTheSameColor = true;
            } else {
                sameColor = 0;
            }

        }
        assertEquals(false, allCardsHaveTheSameColor);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PINK", "BLACK", "BLUE", "YELLOW", "RED", "ORANGE", "WHITE", "GREEN", "RAINBOW"})
    void afterReturnCardSetShouldBeIncrease(String color) {
        int expected = deck.getAmountCards(color);
        expected++;
        deck.addCards(new Card(color));
        int actual = deck.getAmountCards(color);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PINK", "BLACK", "BLUE", "YELLOW", "RED", "ORANGE", "WHITE", "GREEN", "RAINBOW"})
    void takeCarkWhileThereIsNoneInDeck(String color) {
        deck = new Deck(0, 0, 0, 0, 0, 0, 0,
                0, 0);
        assertThrows(NoSuchElementException.class, () -> {deck.getCard(color);});
    }

    @Test
    void takeCardWhereColorDontExist() {
        assertThrows(IllegalArgumentException.class, () -> {deck.getCard("UNICORN");});
    }
}