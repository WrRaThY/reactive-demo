package priv.rdo.reactivedemo.hotel.sync;

import org.springframework.stereotype.Service;
import priv.rdo.reactivedemo.hotel.Hotel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static priv.rdo.reactivedemo.FakeHotelDataProvider.testHotel;
import static priv.rdo.reactivedemo.Sleeper.sleep;

@Service
public class SyncHotelService {
    private final Map<Long, Hotel> sqlDatabase;

    public SyncHotelService() {
        sqlDatabase = new HashMap<>();
        sqlDatabase.put(3L, testHotel(3));
        sqlDatabase.put(5L, testHotel(5));
    }

    public Optional<Hotel> findByIdFromUsualSource(long id) {
        sleep(2);
        return Optional.ofNullable(sqlDatabase.get(id));
    }

    public Collection<Hotel> findAll() {
        sleep(4);
        return sqlDatabase.values();
    }
}
