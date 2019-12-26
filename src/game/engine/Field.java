package game.engine;

import game.engine.tools.GameRules;

import java.util.Random;

public class Field {

    private long fieldId;
    private String color;

    public Field(long fieldId) {
        this.fieldId = fieldId;
        String[] colors = new GameRules().getCardColors();
        color = colors[new Random().nextInt(colors.length + 1)];
    }

    public long getFieldId() {
        return fieldId;
    }

    public String getColor() {
        return color;
    }
}
