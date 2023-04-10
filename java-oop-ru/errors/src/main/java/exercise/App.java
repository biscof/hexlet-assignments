package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) throws NegativeRadiusException {
        try {
            System.out.println(Math.round(circle.getSquare()));
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Вычисление окончено");
    }
}
// END
