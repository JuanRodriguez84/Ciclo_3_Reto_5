package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.custom.CountClientes;
import co.usa.ciclo3.reto5.custom.StatusReservas;
import co.usa.ciclo3.reto5.model.Reservation;
import co.usa.ciclo3.reto5.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation p) {
        if (p.getIdReservation() == null) {
            return reservationRepository.save(p);
        } else {
            Optional<Reservation> paux = reservationRepository.getReservation(p.getIdReservation());
            if (paux.isEmpty()) {
                return reservationRepository.save(p);
            } else {
                return p;
            }
        }
    }

    public Reservation update(Reservation c) {
        if (c.getIdReservation() != null) {
            Optional<Reservation> g = reservationRepository.getReservation(c.getIdReservation());
            if (!g.isEmpty()) {
                if (c.getStartDate() != null) {
                    g.get().setStartDate(c.getStartDate());
                }
                if (c.getDevolutionDate() != null) {
                    g.get().setDevolutionDate(c.getDevolutionDate());
                }
                if (c.getStatus() != null) {
                    g.get().setStatus(c.getStatus());
                }
                if (c.getClient() != null) {
                    g.get().setClient(c.getClient());
                }
                if (c.getSkate() != null) {
                    g.get().setSkate(c.getSkate());
                }
                if (c.getScore() != null) {
                    g.get().setScore(c.getScore());
                }
                return reservationRepository.save(g.get());
            }
        }
        return c;
    }

    public boolean deleteReservation(int id) {
        Boolean d = getReservation(id).map(Reservation -> {
            reservationRepository.delete(Reservation);
            return true;
        }).orElse(false);
        return d;
    }

    public StatusReservas getReporteStatusReservaciones() {
        List<Reservation> completed = reservationRepository.ReservationStatus("completed");
        List<Reservation> cancelled = reservationRepository.ReservationStatus("cancelled");
        return new StatusReservas(completed.size(), cancelled.size());
    }

    public List<Reservation> getReporteTiempoReservaciones(String dateA, String dateB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();

        try {
            datoUno = parser.parse(dateA);
            datoDos = parser.parse(dateB);
        } catch (ParseException e) {
        }
        if (datoUno.before(datoDos)) {
            return reservationRepository.ReservationTime(datoUno, datoDos);
        } else {
            return new ArrayList<>();
        }
    }

    public List<CountClientes> getTopClientes() {
        return reservationRepository.getTopClientes();
    }
}
