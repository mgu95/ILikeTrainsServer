package game.engine;

import java.util.Set;

public class Route /*extends Field*/ {

    private int[][] startPoint;
    private int[][] endPoint;
    private Set<int[][]> track;
    private int size;
    private String color;
    private String owner;

//    public Route(String color) {
//        this.color = color;
//    }

    public int[][] getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int[][] startPoint) {
        this.startPoint = startPoint;
    }

    public int[][] getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int[][] endPoint) {
        this.endPoint = endPoint;
    }

    public Set<int[][]> getTrack() {
        return track;
    }

    public void setTrack(Set<int[][]> track) {
        this.track = track;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
                "size='" + size + '\'' +
                "color='" + color + '\'' +
                "owner='" + owner + '\'' +
                '}';
    }
}
