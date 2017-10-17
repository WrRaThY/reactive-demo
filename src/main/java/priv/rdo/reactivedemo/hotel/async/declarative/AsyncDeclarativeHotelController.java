package priv.rdo.reactivedemo.hotel.async.declarative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.rdo.reactivedemo.hotel.Hotel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("hotels/async/declarative")
public class AsyncDeclarativeHotelController {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncDeclarativeHotelController.class);

    private final AsyncDeclarativeHotelService hotelService;

    public AsyncDeclarativeHotelController(AsyncDeclarativeHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("{id}")
    Mono<Hotel> findById(@PathVariable long id) {
        return hotelService.findById(id);
    }

    @GetMapping
    Flux<Hotel> findAll() {
        LOG.info("entering find all");
        Flux<Hotel> all = hotelService.findAll1();
        LOG.info("returning flux");
        return all;
    }

    @GetMapping("findAll2")
    Flux<Hotel> findAll2() {
        LOG.info("entering find all");
        Flux<Hotel> all = hotelService.findAll2();
        LOG.info("returning flux");
        return all;
    }

}
