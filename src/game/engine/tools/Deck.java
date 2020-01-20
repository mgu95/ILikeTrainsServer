package game.engine.tools;

import game.engine.Card;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Deck {

    private Set<Card> pinkCards = new HashSet<>();
    private Set<Card> blackCards = new HashSet<>();
    private Set<Card> blueCards = new HashSet<>();
    private Set<Card> yellowCards = new HashSet<>();
    private Set<Card> redCards = new HashSet<>();
    private Set<Card> orangeCards = new HashSet<>();
    private Set<Card> whiteCards = new HashSet<>();
    private Set<Card> greenCards = new HashSet<>();
    private Set<Card> rainbowCards = new HashSet<>();

    public Deck() {}

    public Deck(int pinkCards, int blackCards, int blueCards, int yellowCards, int redCards, int orangeCards,
            int whiteCards, int greenCards, int rainbowCards) {
        for (int i = 0; i < pinkCards; i++) {
            this.pinkCards.add(new Card("PINK"));
        }
        for (int i = 0; i < blackCards; i++) {
            this.blackCards.add(new Card("BLACK"));
        }
        for (int i = 0; i < blueCards; i++) {
            this.blueCards.add(new Card("BLUE"));
        }
        for (int i = 0; i < yellowCards; i++) {
            this.yellowCards.add(new Card("YELLOW"));
        }
        for (int i = 0; i < redCards; i++) {
            this.redCards.add(new Card("RED"));
        }
        for (int i = 0; i < orangeCards; i++) {
            this.orangeCards.add(new Card("ORANGE"));
        }
        for (int i = 0; i < whiteCards; i++) {
            this.whiteCards.add(new Card("WHITE"));
        }
        for (int i = 0; i < greenCards; i++) {
            this.greenCards.add(new Card("GREEN"));
        }
        for (int i = 0; i < rainbowCards; i++) {
            this.rainbowCards.add(new Card("RAINBOW"));
        }
    }

    public int getAmountCards(String color) {
        switch (color) {
            case "PINK":
                return pinkCards.size();
            case "BLACK":
                return blackCards.size();
            case "BLUE":
                return blueCards.size();
            case "YELLOW":
                return yellowCards.size();
            case "RED":
                return redCards.size();
            case "ORANGE":
                return orangeCards.size();
            case "WHITE":
                return whiteCards.size();
            case "GREEN":
                return greenCards.size();
            case "RAINBOW":
                return rainbowCards.size();
        }
        throw new IllegalArgumentException("There is no card with the specified color!");
    }

    public Card getCard(String color) {
        Card cardToReturn;
        Iterator<Card> iterator;
        Set<Card> tmpSet = new HashSet<>();
        switch (color) {
            case "PINK":
                iterator = pinkCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                pinkCards.clear();
                pinkCards.addAll(tmpSet);
                return cardToReturn;
            case "BLACK":
                iterator = blackCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                blackCards.clear();
                blackCards.addAll(tmpSet);
                return cardToReturn;
            case "BLUE":
                iterator = blueCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                blueCards.clear();
                blueCards.addAll(tmpSet);
                return cardToReturn;
            case "YELLOW":
                iterator = yellowCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                yellowCards.clear();
                yellowCards.addAll(tmpSet);
                return cardToReturn;
            case "RED":
                iterator = redCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                redCards.clear();
                redCards.addAll(tmpSet);
                return cardToReturn;
            case "ORANGE":
                iterator = orangeCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                orangeCards.clear();
                orangeCards.addAll(tmpSet);
                return cardToReturn;
            case "WHITE":
                iterator = whiteCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                whiteCards.clear();
                whiteCards.addAll(tmpSet);
                return cardToReturn;
            case "GREEN":
                iterator = greenCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                greenCards.clear();
                greenCards.addAll(tmpSet);
                return cardToReturn;
            case "RAINBOW":
                iterator = rainbowCards.iterator();
                cardToReturn = iterator.next();
                while (iterator.hasNext()) {
                    tmpSet.add(iterator.next());
                }
                rainbowCards.clear();
                rainbowCards.addAll(tmpSet);
                return cardToReturn;
        }
        throw new IllegalArgumentException("The deck has no cards with a specific color!");
    }

    public Card[] getRandomCards(int amount) {
        Set<Card> allCardsFromDesk = new HashSet<>();
        allCardsFromDesk.addAll(pinkCards);
        allCardsFromDesk.addAll(blackCards);
        allCardsFromDesk.addAll(blueCards);
        allCardsFromDesk.addAll(yellowCards);
        allCardsFromDesk.addAll(redCards);
        allCardsFromDesk.addAll(orangeCards);
        allCardsFromDesk.addAll(whiteCards);
        allCardsFromDesk.addAll(greenCards);
        allCardsFromDesk.addAll(rainbowCards);

        Card[] randomCards = new Card[amount];
        Iterator<Card> iterator = allCardsFromDesk.iterator();
        for (int i = 0; i < randomCards.length; i++) {
            randomCards[i] = iterator.next();
        }
        pinkCards.clear();
        blackCards.clear();
        blueCards.clear();
        yellowCards.clear();
        redCards.clear();
        orangeCards.clear();
        whiteCards.clear();
        greenCards.clear();
        rainbowCards.clear();
        while (iterator.hasNext()) {
            addCards(iterator.next());
        }

        return randomCards;
    }

    public void addCards(Card... cards) {
        for (Card card : cards) {
            switch (card.getColor()) {
                case "PINK":
                    pinkCards.add(card);
                    break;
                case "BLACK":
                    blackCards.add(card);
                case "BLUE":
                    blueCards.add(card);
                    break;
                case "YELLOW":
                    yellowCards.add(card);
                    break;
                case "RED":
                    redCards.add(card);
                    break;
                case "ORANGE":
                    orangeCards.add(card);
                    break;
                case "WHITE":
                    whiteCards.add(card);
                    break;
                case "GREEN":
                    greenCards.add(card);
                    break;
                case "RAINBOW":
                    rainbowCards.add(card);
                    break;
            }
        }

    }

    @Override
    public String toString() {
        return "Deck{pinkCards=" + pinkCards.size() + "blackCards=" + blackCards.size()
                + ", blueCards=" + blueCards.size() + ", yellowCards=" + yellowCards.size()
                + ", redCards=" + redCards.size() + ", orangeCards=" + orangeCards.size()
                + ", whiteCards=" + whiteCards.size() + ", greenCards=" + greenCards.size()
                + ", rainbowCards=" + rainbowCards.size() + ", total="
                + (pinkCards.size() + blackCards.size() + blueCards.size() + yellowCards.size() + redCards.size()
                + orangeCards.size() + whiteCards.size() + greenCards.size() + rainbowCards.size()) + "}";
    }
}
