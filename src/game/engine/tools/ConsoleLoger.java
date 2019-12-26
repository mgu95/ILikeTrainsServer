package game.engine.tools;

public class ConsoleLoger implements Loger {

    private boolean active;

    public ConsoleLoger(boolean active) {
        this.active = active;
    }

    @Override
    public void addToLog(String... logs) {

        if (logs.length > 1) {
            StringBuilder space = new StringBuilder();
            for (int i = 0; i < getCurrentDateAndTime().length(); i++) {
                space.append(" ");
            }
            this.log.append(getCurrentDateAndTime() + logs[0] + "\n");
            for (int i = 1; i < logs.length; i++) {
                this.log.append(space + logs[i] + "\n");
            }
        } else {
            this.log.append(getCurrentDateAndTime() + logs[0] + "\n");
        }
    }

    @Override
    public void print() {

        if (active) {
            System.out.println(log);
            this.log.setLength(0);
        }
    }
}
