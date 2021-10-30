package co.usa.ciclo3.reto5.web;

import co.usa.ciclo3.reto5.custom.CountClientes;
import co.usa.ciclo3.reto5.custom.StatusReservas;
import co.usa.ciclo3.reto5.model.Reservation;
import co.usa.ciclo3.reto5.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getReservations() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationService.getReservation(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation p) {
        return reservationService.save(p);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation c) {
        return reservationService.update(c);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReservation(@PathVariable("id") int id) {
        return reservationService.deleteReservation(id);
    }

    @GetMapping("/report-status")
    public StatusReservas getReservas() {
        return reservationService.getReporteStatusReservaciones();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservasTiempo(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo) {
        return reservationService.getReporteTiempoReservaciones(dateOne, dateTwo);
    }

    @GetMapping("report-clients")
    public List<CountClientes> getClientes() {
        return reservationService.getTopClientes();
    }

}
