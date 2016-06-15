package oo;

import com.google.common.collect.Lists;
import oo.finder.ParkLotFinder;

import java.util.List;
import java.util.Optional;

public class ParkBoy implements Parkable {

    private final List<ParkLot> parkLots;
    private final ParkLotFinder parkLotFinder;

    public ParkBoy(ParkLotFinder parkLotFinder, ParkLot... parkLots) {
        this.parkLots = Lists.newArrayList(parkLots);
        this.parkLotFinder = parkLotFinder;
    }

    public Optional<String> park(Car car) {
        return parkLotFinder.findParkLot(this.parkLots).park(car);
    }

    public Optional<Car> unPark(String ticket) {
        return getParkLots().stream()
                .map(parkLot -> parkLot.unPark(ticket))
                .filter(Optional::isPresent)
                .findFirst()
                .orElse(Optional.empty());
    }

    @Override
    public String report(IReporter reporter) {
        return reporter.report(this);
    }

    public List<ParkLot> getParkLots() {
        return parkLots;
    }
}
