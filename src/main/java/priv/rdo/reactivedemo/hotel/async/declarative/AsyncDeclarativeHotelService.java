package priv.rdo.reactivedemo.hotel.async.declarative;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import priv.rdo.reactivedemo.FakeHotelDataProvider;
import priv.rdo.reactivedemo.hotel.Hotel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static priv.rdo.reactivedemo.Sleeper.sleep;

@Service
public class AsyncDeclarativeHotelService {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncDeclarativeHotelService.class);

    public Mono<Hotel> findById(long id) {
        return Mono.fromCallable(
                () -> doFind(id)
        ).subscribeOn(Schedulers.elastic());
    }

    private Hotel doFind(long id) {
        sleep(2);
        if (id == 3L || id == 5L) {
            return FakeHotelDataProvider.testHotel((int) id);
        } else {
            return null;
        }
    }

    public Flux<Hotel> findAll1() {
        return Flux.interval(Duration.ofSeconds(2))
                .takeWhile(i -> i < 3)
                .map(i -> FakeHotelDataProvider.testHotel(i.intValue()))
                .doOnEach(item -> LOG.info("hotel added to flux {}", item.get()));
    }

    public Flux<Hotel> findAll2() {
        return Flux.generate(new Consumer<SynchronousSink<Hotel>>() {
            AtomicInteger counter = new AtomicInteger(0);

            @Override
            public void accept(SynchronousSink<Hotel> hotelSynchronousSink) {
                sleep(2);
                if (counter.get() == 3) {
                    LOG.info("flux complete");
                    hotelSynchronousSink.complete();
                } else {
                    Hotel hotel = FakeHotelDataProvider.testHotel(counter.getAndIncrement());
                    LOG.info("adding hotel {}", hotel);
                    hotelSynchronousSink.next(hotel);
                }
            }
        });
    }

    public Flux<Hotel> findAll3() {
        FakeHotelGenerator generator = new FakeHotelGenerator();
        return Mono.just(generator.generateHotel()) //don't use it like that. it will just send the same data over and over again
                .delayElement(Duration.ofSeconds(2))
                .repeat(3);
    }

    public Flux<Hotel> findAll4() {
        FakeHotelGenerator generator = new FakeHotelGenerator();
        return Mono.fromCallable(generator::generateHotel)
                .log() //TODO: this taps into onNext/onComplete and so on somehow. code leads to onAssembly. dig deeper
                .doOnEach(item -> LOG.info("hotel added to flux {}", item.get()))
                .delayElement(Duration.ofSeconds(2))
                .repeat(3);
    }


}
