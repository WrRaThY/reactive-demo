package priv.rdo.reactivedemo.hotel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Hotel {
    private long id;
    private String name;
    private String address;
    private List<Room> rooms;

    public Hotel(String name, String address, List<Room> rooms) {
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }
}
