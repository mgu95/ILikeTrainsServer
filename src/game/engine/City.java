package game.engine;

public class City extends Field {

    private long cityId;
    private String name;
    private String owner;

    public City(Field field, long cityId, String name) {

        super(field.getFieldId(), field.getBoardWidth(), field.getBoardHeight());
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
                "fieldId=" + getFieldId() +
                ", boardHeight=" + getBoardHeight() +
                ", boardWidth=" + getBoardWidth() +
                ", cityId=" + cityId +
                ", name=" + name +
                ", owner=" + owner +
                '}';
    }
}
