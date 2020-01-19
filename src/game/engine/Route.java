package game.engine;

public class Route extends Field {

    private long routeId;
    private String color;
    private String owner;

    public Route(Field field, long routeId, String color) {
        super(field.getFieldId(), field.getXPosition(), field.getYPosition());
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

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Route{" +
                "fieldId=" + getFieldId() +
                ", yPosition=" + getYPosition() +
                ", xPosition=" + getXPosition() +
                ", routeId=" + routeId +
                ", color='" + color + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
