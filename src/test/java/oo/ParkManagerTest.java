package oo;


import oo.finder.NormalParkLotFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkManagerTest {

    private Parkable parkable;

    @Before
    public void setUp() throws Exception {
        ParkLot parkLot1 = new ParkLot(2);
        ParkLot parkLot2 = new ParkLot(2);

        ParkLot parkLot3 = new ParkLot(2);
        ParkLot parkLot4 = new ParkLot(2);

        Parkable parkable1 = new ParkBoy(new NormalParkLotFinder(), parkLot1, parkLot2);
        Parkable parkable2 = new ParkBoy(new NormalParkLotFinder(), parkLot3, parkLot4);

        parkable = new ParkManager(parkable1, parkable2);
    }

    @Test
    public void should_park_success_when_all_park_lot_is_not_full() throws Exception {
        Optional<String> park = parkable.park(new Car("1"));

        assertThat(park.isPresent(), is(true));
    }

    @Test
    public void should_park_failed_when_all_park_lot_is_full() throws Exception {
        parkable.park(new Car("1"));
        parkable.park(new Car("2"));
        parkable.park(new Car("3"));
        parkable.park(new Car("4"));
        parkable.park(new Car("5"));
        parkable.park(new Car("6"));
        parkable.park(new Car("7"));
        parkable.park(new Car("8"));

        Optional<String> park = parkable.park(new Car("9"));

        assertThat(park.isPresent(), is(false));
    }

    @Test
    public void should_un_park_success_when_park_lot_has_car() throws Exception {
        Car car = new Car("1");
        Optional<String> park = parkable.park(car);

        Optional<Car> unPark = parkable.unPark(park.get());

        assertThat(unPark.get(), is(car));
    }

    @Test
    public void should_un_park_failed_when_park_lot_has_no_car() throws Exception {
        Optional<Car> unPark = parkable.unPark("1");

        assertThat(unPark.isPresent(), is(false));
    }

    @Test
    public void should_generate_right_report() throws Exception {

        String report = parkable.report(new Reporter(0, new StringBuilder()));

        assertThat(report, is(
                "ParkManager:\n" +
                "-ParkBoy:\n" +
                "--ParkLot(0/2)\n" +
                "--ParkLot(0/2)\n" +
                "-ParkBoy:\n" +
                "--ParkLot(0/2)\n" +
                "--ParkLot(0/2)\n"));
    }

}