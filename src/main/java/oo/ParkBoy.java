package oo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

public class ParkBoy {
    private final List<ParkLot> parkLots;

    public ParkBoy(ParkLot... parkLots) {
        this.parkLots = Lists.newArrayList(parkLots);
    }

    public Optional<String> park(Car car) {
        ParkLot parkLot = this.parkLots.stream().
                filter(p -> p.getRestSpace() > 0).
                findFirst().
                orElse(parkLots.get(0));
        return parkLot.park(car);
    }

    public Optional<Car> unPark(String ticket) {
        return this.parkLots.stream().map(parkLot -> parkLot.unPark(ticket)).
                filter(car -> car.isPresent()).
                findFirst().
                orElse(Optional.empty());
    }
}
