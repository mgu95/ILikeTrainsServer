package game.engine.tools;

import game.engine.Card;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Deck {

    private Set<Card> cards = new HashSet<>();

    public Deck(int rainbowCards, int otherCards) {
        String[] cardsColor = GameRules.getCardColors();
        for (String color : cardsColor) {
            if (color.equals("RAINBOW")) {
                for (int i = 0; i < rainbowCards; i++) {
                    cards.add(new Card(color));
                }
            } else {
                for (int i = 0; i < otherCards; i++) {
                    cards.add(new Card(color));
                }
            }
        }
    }

    public int getAmountCards(String color) {
        int amount = 0;
        for (Card card : cards) {
            if (card.getColor().equals(color)) {
                amount++;
            }
        }
        return amount;
    }

    public Card takeCard(String color) {
        boolean takeCard = false;
        Card cardToReturn = new Card("null");
        Iterator<Card> iterator = cards.iterator();
        Set<Card> tmpDeck = new HashSet<>();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getColor().equals(color) && takeCard == false) {
                cardToReturn = card;
                takeCard = true;
            } else {
                tmpDeck.add(card);
            }
        }
        cards = tmpDeck;
        return cardToReturn;
    }

    public void returnCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Deck{");
        String[] cardsColor = GameRules.getCardColors();
        int deckSize = 0;
        for (String color : cardsColor) {
            int amount = getAmountCards(color);
            stringBuilder.append(color + "=" + amount + ", ");
            deckSize += amount;
        }
        stringBuilder.append("total=" + deckSize + "}");
        return stringBuilder.toString();
    }
}
