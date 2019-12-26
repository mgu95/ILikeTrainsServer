package game.engine.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Loger {

    SimpleDateFormat formatter = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']' ");
    StringBuilder log = new StringBuilder();

    public void addToLog(String... logs);
    public void print();

    public default String getCurrentDateAndTime() {
        return formatter.format(new Date(System.currentTimeMillis()));
    }
}
