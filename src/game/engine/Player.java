package game.engine;

import game.engine.tools.Deck;

import java.util.Set;

public class Player {

    private String login;
    private Deck deck;

    public Player(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public String toString() {
        return "Player{" +
                "login='" + login + '\'' +
                '}';
    }
}
