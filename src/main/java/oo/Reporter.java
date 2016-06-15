package oo;

import com.google.common.base.Strings;

public class Reporter implements IReporter {
    private final int level;
    private final StringBuilder result;

    public Reporter(int level, StringBuilder result) {
        this.level = level;
        this.result = result;
    }

    @Override
    public String report(ParkLot parkLot) {
        this.result.append(Strings.repeat("-", level))
                .append(String.format("ParkLot(%d/%d)\n", parkLot.getUsedSpaceSize(), parkLot.getParkLotSize()));
        return this.result.toString();
    }

    @Override
    public String report(ParkBoy parkBoy) {
        this.result.append(Strings.repeat("-", level))
                .append("ParkBoy:\n");

        parkBoy.getParkLots()
                .stream()
                .forEach(parkLot -> parkLot.report(new Reporter(level + 1, this.result)));

        return this.result.toString();
    }

    @Override
    public String report(ParkManager parkManager) {
        this.result.append(Strings.repeat("-", level))
                .append("ParkManager:\n");

        parkManager.getParkables()
                .stream()
                .forEach(parkable -> parkable.report(new Reporter(level + 1, this.result)));

        return this.result.toString();
    }
}
