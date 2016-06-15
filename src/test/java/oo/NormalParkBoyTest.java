package oo;

import oo.finder.NormalParkLotFinder;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NormalParkBoyTest {

    @Test
    public void should_park_success_in_the_parkLot_with_max_rest_space() throws Exception {
        ParkLot parkLot1 = new ParkLot(2);
        ParkLot parkLot2 = new ParkLot(3);
        Parkable normalParkable = new ParkBoy(new NormalParkLotFinder(), parkLot1, parkLot2);

        Car car = new Car("1");
        Optional<String> ticket = normalParkable.park(car);

        assertThat(ticket.isPresent(), is(true));
        assertThat(parkLot2.unPark(ticket.get()).get(), is(car));
    }

}
