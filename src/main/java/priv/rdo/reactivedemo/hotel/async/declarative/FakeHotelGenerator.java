package priv.rdo.reactivedemo.hotel.async.declarative;

import priv.rdo.reactivedemo.FakeHotelDataProvider;
import priv.rdo.reactivedemo.hotel.Hotel;

import java.util.concurrent.atomic.AtomicInteger;

import static priv.rdo.reactivedemo.Sleeper.sleep;

public class FakeHotelGenerator {
    AtomicInteger counter = new AtomicInteger(0);

    public Hotel generateHotel() {
        return FakeHotelDataProvider.testHotel(counter.getAndIncrement());
    }

    public Hotel generateHotelWithDelay() {
        sleep(2);
        return FakeHotelDataProvider.testHotel(counter.getAndIncrement());
    }
}
