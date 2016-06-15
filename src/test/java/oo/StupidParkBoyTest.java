package oo;

import oo.finder.StupidParkLotFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StupidParkBoyTest {
    private Parkable pb;
    private ParkLot parkLot1;
    private ParkLot parkLot2;


    @Before
    public void setUp() throws Exception {
        parkLot1 = new ParkLot(2);
        parkLot2 = new ParkLot(2);
        pb = new ParkBoy(new StupidParkLotFinder(), parkLot1, parkLot2);
    }

    @Test
    public void should_park_in_first_not_full_parkLot() throws Exception {
        Car car = new Car("1");

        Optional<String> ticket = pb.park(car);

        assertThat(ticket.isPresent(), is(true));
        assertThat(parkLot1.unPark(ticket.get()).get(), is(car));
    }

    @Test
    public void should_park_in_second_parkLot_when_first_parkLot_is_full() throws Exception {

        pb.park(new Car("1"));
        pb.park(new Car("2"));
        Car car = new Car("3");
        Optional<String> ticket = pb.park(car);

        assertThat(ticket.isPresent(), is(true));
        assertThat(parkLot2.unPark(ticket.get()).get(), is(car));
    }


    @Test
    public void should_park_failed_when_all_parkLots_is_full() throws Exception {

        pb.park(new Car("1"));
        pb.park(new Car("2"));
        pb.park(new Car("3"));
        pb.park(new Car("4"));
        Optional<String> ticket = pb.park(new Car("5"));

        assertThat(ticket.isPresent(), is(false));
    }

    @Test
    public void should_unPark_success_when_car_is_in_parkLot() throws Exception {
        Car car = new Car("1");
        Optional<String> ticket = pb.park(car);

        Optional<Car> carOptional = pb.unPark(ticket.get());

        assertThat(carOptional.get(), is(car));
    }

    @Test
    public void should_unPark_failed_when_car_is_not_in_parkLot() throws Exception {

        Optional<Car> carOptional = pb.unPark("1");

        assertThat(carOptional.isPresent(), is(false));
    }

    @Test
    public void should_generate_right_report() throws Exception {

        String report = pb.report(new Reporter(0, new StringBuilder()));

        assertThat(report, is(
                "ParkBoy:\n" +
                "-ParkLot(0/2)\n" +
                "-ParkLot(0/2)\n"));
    }
}
