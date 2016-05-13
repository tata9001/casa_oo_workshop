package oo;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParkLotTest {

    private ParkLot p;

    @Before
    public void setUp() throws Exception {
        p = new ParkLot(2);
    }


    @Test
    public void shouldParkSuccessWhenParklotIsEmpty() throws Exception {
        Optional<String> ticket = p.park(new Car("1"));
        assertThat(ticket.isPresent(), is(true));
    }

    @Test
    public void shouldParkSuccessWhenParklotHasCar() throws Exception {
        p.park(new Car("1"));

        Optional<String> ticket2 = p.park(new Car("2"));

        assertThat(ticket2.isPresent(), is(true));
    }

    @Test
    public void shouldParkFailedWhenParklotIsFull() throws Exception {
        p.park(new Car("1"));
        p.park(new Car("2"));

        Optional<String> ticket3 = p.park(new Car("3"));
        assertThat(ticket3.isPresent(), is(false));
    }

    @Test
    public void shouldUnParkSuccessWhenParklotHasCar() throws Exception {
        Car car1 = new Car("1");
        Optional<String> ticket = p.park(car1);
        Optional<Car> car = p.unPark(ticket.get());
        assertThat(car.get(), is(car1));
    }

    @Test
    public void shouldUnParkFailedWhenParklotNotHasCar() throws Exception {
        Optional<Car> car = p.unPark("1");
        assertThat(car.isPresent(), is(false));
    }

    @Test
    public void shouldParkSuccessAgainWhenParklotIsNotFull() throws Exception {
        Car car1 = new Car("1");
        Optional<String> ticket = p.park(car1);
        Optional<Car> car = p.unPark(ticket.get());
        Optional<String> ticket2 = p.park(car.get());
        assertThat(ticket2.isPresent(), is(true));
    }

}
