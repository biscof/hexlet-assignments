package exercise;

// BEGIN
public class Circle {
    Point centre;
    int radius;

    public Circle(Point centre, int radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        } else {
            return Math.pow(radius, 2) * Math.PI;
        }
    }
}
// END
