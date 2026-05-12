package roomescape;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.domain.Reservation;

@Controller
public class RoomescapeController {

    private final List<Reservation> reservations = new ArrayList<>();

    @GetMapping("/")
    public String getIndexPage() {
        return "home";
    }

    @GetMapping("/reservation")
    public String getReservationPage() {
        return "reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation reservation1 = new Reservation((long) reservations.size() + 1, reservation.getName(),
            reservation.getDate(), reservation.getTime());
        reservations.add(reservation1);
        return ResponseEntity
            .created(URI.create("/reservations/" + reservations.size()))
            .body(reservation1);
    }

    @DeleteMapping("/reservations/{reservationId}")
    @ResponseBody
    public ResponseEntity<Void> addReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservations.stream()
            .filter(o -> o.getId() == reservationId)
            .findFirst()
            .get();
        reservations.remove(reservation);
        return ResponseEntity.noContent().build();
    }
}
