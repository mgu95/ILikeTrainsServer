package game.engine;

public class Field {

    private long fieldId;
    private int boardWidth;
    private int boardHeight;

    public Field(long fieldId, int boardWidth, int boardHeight) {
        this.fieldId = fieldId;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public long getFieldId() {
        return fieldId;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldId=" + fieldId +
                ", boardHeight=" + boardHeight +
                ", boardWidth=" + boardWidth +
                '}';
    }
}
