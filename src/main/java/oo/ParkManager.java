package oo;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

public class ParkManager implements Parkable {
    private final List<Parkable> parkables;

    public ParkManager(Parkable... parkables) {
        this.parkables = Lists.newArrayList(parkables);
    }

    @Override
    public Optional<String> park(Car car) {
        return parkables.stream()
                .map(parkable -> parkable.park(car))
                .limit(1)
                .findAny()
                .orElse(Optional.empty());
    }

    @Override
    public Optional<Car> unPark(String ticket) {
        return parkables.stream()
                .map(parkBoy -> parkBoy.unPark(ticket))
                .filter(Optional::isPresent)
                .findFirst()
                .orElse(Optional.empty());
    }

    @Override
    public String report(IReporter reporter) {
        return reporter.report(this);
    }

    public List<Parkable> getParkables() {
        return parkables;
    }
}
