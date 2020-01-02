package game.engine;

public class Field {

    private long fieldId;
    private int xPosition;
    private int yPosition;

    public Field(long fieldId, int xPosition, int yPosition) {
        this.fieldId = fieldId;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public long getFieldId() {
        return fieldId;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldId=" + fieldId +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }
}
