package priv.rdo.reactivedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.rdo.reactivedemo.hotel.Hotel;
import priv.rdo.reactivedemo.hotel.Room;

import java.util.Collections;

public class FakeHotelDataProvider {
    private static final Logger LOG = LoggerFactory.getLogger(FakeHotelDataProvider.class);

    public static Hotel testHotel(int counter) {
        Room room = new Room(13L + counter, 3 + counter);
        room.setId(5L + counter);

        Hotel hotel = new Hotel("someHotelName" + counter, "someAddress" + counter, Collections.singletonList(room));
        hotel.setId((long) counter);

        LOG.info("fake hotel generated {}" + hotel);
        return hotel;
    }
}
