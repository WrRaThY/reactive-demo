package priv.rdo.reactivedemo.hotel.sync;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.rdo.reactivedemo.hotel.Hotel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("hotels/sync")
public class SyncHotelController {
    private final SyncHotelService hotelService;

    public SyncHotelController(SyncHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("{id}")
    /**
     * yes, its still 200 if response is empty
     */
    Mono<Hotel> findById(@PathVariable long id) {
        return Mono.justOrEmpty(hotelService.findByIdFromUsualSource(id));
    }

    @GetMapping
    Flux<Hotel> findAll() {
        return Flux.fromIterable(hotelService.findAll());
    }

}
