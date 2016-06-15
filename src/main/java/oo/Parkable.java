package oo;

import java.util.Optional;

public interface Parkable extends Reportable {
    Optional<String> park(Car car);

    Optional<Car> unPark(String ticket);

}
