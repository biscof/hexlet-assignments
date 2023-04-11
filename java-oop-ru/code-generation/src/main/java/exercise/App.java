package exercise;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void main(String[] args) {
        Car car = new Car(
                45,
                "volvo",
                "V90",
                "yellow",
                new User(
                        122,
                        "John",
                        "Brown",
                        60)
                );

        String str = car.serialize();
        System.out.println(Car.unserialize(str).getBrand());
        System.out.println(Car.unserialize(str).getModel());
    }

    public static void save(Path path, Car car) {
        String jsonStr = car.serialize();
        try {
            Files.writeString(path, jsonStr, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static Car extract(Path path) {
        String jsonStr = "";
        try {
            jsonStr = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return Car.unserialize(jsonStr);
    }
}
// END
