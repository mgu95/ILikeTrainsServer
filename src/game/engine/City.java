package game.engine;

import java.util.HashSet;
import java.util.Set;

public class City extends Field {

    private long cityId;
    private String name;
    private String owner;
    private Set<Long> routesIds = new HashSet<>();

    public City(Field field, long cityId, String name) {

        super(field.getFieldId(), field.getBoardWidth(), field.getBoardHeight());
        this.cityId = cityId;
        this.name = name;
    }

    public void addRouteId(long routeId) {
        routesIds.add(routeId);
    }

    public long getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

        @Override
    public String toString() {
        return "City{" +
                "fieldId=" + getFieldId() +
                ", boardHeight=" + getBoardHeight() +
                ", boardWidth=" + getBoardWidth() +
                ", cityId=" + cityId +
                ", name=" + name +
                ", owner=" + owner +
                ", routesIds=" + routesIds +
                '}';
    }
}
