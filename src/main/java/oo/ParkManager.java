package oo;


import com.google.common.base.Strings;
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
    public String report(int level) {
        StringBuilder result = new StringBuilder();
        result.append(Strings.repeat("-", level))
                .append("ParkManager:\n");

        this.getParkables()
                .stream()
                .forEach(parkable -> result.append(parkable.report(level + 1)));

        return result.toString();
    }

    public List<Parkable> getParkables() {
        return parkables;
    }
}
