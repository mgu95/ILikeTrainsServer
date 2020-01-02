package game.engine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class City extends Field {

    private long cityId = 1;
    private String name;
    private String owner;
    private Set<Long> routesIds = new HashSet<>();

    public City(Field field, long cityId, String name) {

        super(field.getFieldId(), field.getXPosition(), field.getYPosition());
        this.cityId = cityId;
        this.name = name;
    }

    public void addRouteId(long routeId) {
        routesIds.add(routeId);
    }

    public long[] getRoutesId() {
        long[] arr = new long[routesIds.size()];
        Iterator<Long> iterator = routesIds.iterator();
        for (int i = 0; i < arr.length; i++) {
            long l = iterator.next();
            arr[i] = l;
        }
        return arr;
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
                ", yPosition=" + getYPosition() +
                ", xPosition=" + getXPosition() +
                ", cityId=" + cityId +
                ", name=" + name +
                ", owner=" + owner +
                ", routesIds=" + routesIds +
                '}';
    }
}
