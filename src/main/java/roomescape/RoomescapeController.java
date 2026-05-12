package roomescape;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Reservation> getReservationPage2() {
        return reservations;
    }
}
