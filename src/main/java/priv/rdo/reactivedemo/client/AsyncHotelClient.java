package priv.rdo.reactivedemo.client;

import org.springframework.web.reactive.function.client.WebClient;

import static priv.rdo.reactivedemo.Sleeper.sleep;

public class AsyncHotelClient {
    public static void main(String[] args) {
        WebClient client = WebClient.builder()
                .baseUrl("localhost:8080")

                .build();
//
//        Flux<Hotel> hotelFlux = client.get()
//                .uri("hotels/async/declarative")
//                .accept(APPLICATION_JSON)
//                .exchange()
//                .then();



        System.out.println();
        sleep(4);
        System.out.println();
    }
}
