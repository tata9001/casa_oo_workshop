package oo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

public class SmartParkBoy extends ParkBoy {
    public SmartParkBoy(ParkLot... parkLots) {
        super(parkLots);
    }

    @Override
    public Optional<String> park(Car car) {
        List<ParkLot> parkLots = getParkLots();
        ParkLot parkLot = Lists.newArrayList(parkLots).stream().
                sorted((o1, o2) -> Double.compare(o2.getRestSpaceRate(), o1.getRestSpaceRate())).
                findFirst().
                orElse(parkLots.get(0));

        return parkLot.park(car);
    }
}
