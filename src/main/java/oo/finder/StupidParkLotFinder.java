package oo.finder;

import oo.ParkLot;

import java.util.List;

public class StupidParkLotFinder implements ParkLotFinder {

    @Override
    public ParkLot findParkLot(List<ParkLot> parkLots) {
        return parkLots.stream().
                filter(p -> p.getRestSpace() > 0).
                findFirst().
                orElse(parkLots.get(0));
    }
}