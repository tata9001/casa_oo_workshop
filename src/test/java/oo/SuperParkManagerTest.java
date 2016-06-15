package oo;

import oo.finder.NormalParkLotFinder;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SuperParkManagerTest {

    @Test
    public void should_park_success() throws Exception {
        Parkable parkable = new ParkBoy(new NormalParkLotFinder(), new ParkLot(1));
        Parkable pm = new ParkManager(parkable);
        Parkable spm = new ParkManager(pm);

        Optional<String> park = spm.park(new Car("1"));

        assertThat(park.isPresent(), is(true));
    }

    @Test
    public void should_un_park_success() throws Exception {
        Parkable parkable = new ParkBoy(new NormalParkLotFinder(), new ParkLot(1));
        Parkable pm = new ParkManager(parkable);
        Parkable spm = new ParkManager(pm);

        Car car = new Car("1");
        Optional<String> park = spm.park(car);

        Optional<Car> carOptional = spm.unPark(park.get());

        assertThat(carOptional.get(), is(car));
    }
}