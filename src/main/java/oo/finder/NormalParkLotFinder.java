package oo.finder;

import com.google.common.collect.Lists;
import oo.ParkLot;

import java.util.List;

public class NormalParkLotFinder implements ParkLotFinder {

    @Override
    public ParkLot findParkLot(List<ParkLot> parkLots) {
        return Lists.newArrayList(parkLots).
                stream().
                sorted((o1, o2) -> Integer.compare(o2.getRestSpace(), o1.getRestSpace())).
                findFirst().
                orElse(parkLots.get(0));
    }
}