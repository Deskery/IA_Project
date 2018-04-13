public class Location {
    private float x;
    private float y;

    public Location(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double distanceTo(Location location) {
        return Math.sqrt(Math.pow(this.x - location.getX(), 2) + Math.pow(this.y - location.getY(), 2));
    }
}
