package game.engine;

import java.util.Set;

public class Route extends Field {

    private long routeId;
    private String color;
    private String owner;

    public Route(Field field, long routeId, String color) {
        super(field.getFieldId(), field.getBoardWidth(), field.getBoardHeight());
        this.routeId = routeId;
        this.color = color;
    }

    public long getRouteId() {
        return routeId;
    }

    public String getColor() {
        return color;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Route{" +
                "fieldId=" + getFieldId() +
                ", boardHeight=" + getBoardHeight() +
                ", boardWidth=" + getBoardWidth() +
                ", routeId=" + routeId +
                ", color='" + color + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
