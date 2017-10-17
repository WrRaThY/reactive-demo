package priv.rdo.reactivedemo.hotel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Room {
    private Long id;
    private Long roomNumber;
    private Integer maxGuests;

    public Room(Long roomNumber, Integer maxGuests) {
        this.roomNumber = roomNumber;
        this.maxGuests = maxGuests;
    }
}
