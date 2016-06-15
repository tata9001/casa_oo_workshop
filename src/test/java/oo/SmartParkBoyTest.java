package oo;

import oo.finder.SmartParkLotFinder;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SmartParkBoyTest {
    @Test
    public void should_park_success_in_parkLot_with_the_max_rest_space_rate() throws Exception {
        ParkLot parkLot1 = new ParkLot(2);
        parkLot1.park(new Car("1"));

        ParkLot parkLot2 = new ParkLot(3);
        parkLot2.park(new Car("2"));

        Car car = new Car("3");
        Parkable smartParkable = new ParkBoy(new SmartParkLotFinder(), parkLot1, parkLot2);
        Optional<String> ticket = smartParkable.park(car);

        assertThat(ticket.isPresent(), is(true));
        assertThat(parkLot2.unPark(ticket.get()).get(), is(car));

    }

}
