package game.engine;

public class Player {

    private String login;

    public Player(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "Player{" +
                "login='" + login + '\'' +
                '}';
    }
}
