package shika.jsf_site2.model;

public class Point {
    private Double x;
    private Double y;
    private Double r;
    private boolean hit;

    public Point() {}

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void checkHit() {
        this.hit = ((x <= 0 && y >= 0 && (y - 2 * x <= r)) ||
                   (x >= 0 && y >= 0 && y <= r && x <= r) ||
                   (x >= 0 && y <= 0 && (x * x + y * y <= (r / 2) * (r / 2))));
    }
}
