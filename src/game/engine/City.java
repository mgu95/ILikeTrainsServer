package game.engine;

public class City extends Field {

    private long cityId;
    private String name;
    private String owner;

    public City(long fieldId, long cityId, String name) {

        super(fieldId);
        this.cityId = cityId;
        this.name = name;
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
                "name='" + name + '\'' +
                '}';
    }
}
