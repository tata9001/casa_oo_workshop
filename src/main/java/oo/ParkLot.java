package oo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkLot implements Reportable {
    private final int num;
    private Map<String, Car> cars = new HashMap<>();

    public ParkLot(int num) {
        this.num = num;
    }

    public Optional<String> park(Car car) {
        if (this.cars.size() >= num) {
            return Optional.empty();
        }
        this.cars.put(car.getId(), car);
        return Optional.of(car.getId());
    }

    public Optional<Car> unPark(String ticket) {
        return Optional.ofNullable(cars.get(ticket));
    }

    public int getRestSpace() {
        return num - this.cars.size();
    }

    public double getRestSpaceRate() {
        return (((double) getRestSpace()) / num);
    }

    public String report(IReporter reporter) {
        return reporter.report(this);
    }

    public int getUsedSpaceSize() {
        return cars.size();
    }

    public int getParkLotSize() {
        return num;
    }
}
