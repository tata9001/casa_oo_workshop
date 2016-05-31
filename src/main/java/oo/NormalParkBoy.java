package oo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

public class NormalParkBoy extends ParkBoy {

    public NormalParkBoy(ParkLot... parkLots) {
        super(parkLots);
    }

    @Override
    public Optional<String> park(Car car) {
        List<ParkLot> parkLots = getParkLots();
        ParkLot parkLot = Lists.newArrayList(parkLots).
                stream().
                sorted((o1, o2) -> Integer.compare(o2.getRestSpace(), o1.getRestSpace())).
                findFirst().
                orElse(parkLots.get(0));

        return parkLot.park(car);
    }
}
