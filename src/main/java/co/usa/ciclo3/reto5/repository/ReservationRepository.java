package co.usa.ciclo3.reto5.repository;

import co.usa.ciclo3.reto5.custom.CountClientes;
import co.usa.ciclo3.reto5.model.Client;
import co.usa.ciclo3.reto5.model.Reservation;
import co.usa.ciclo3.reto5.repository.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation p) {
        return reservationCrudRepository.save(p);
    }

    public void delete(Reservation c) {
        reservationCrudRepository.delete(c);
    }
    
      public List<Reservation> ReservationStatus (String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> ReservationTime (Date a, Date b) {
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<CountClientes> getTopClientes() {
        List<CountClientes> res=new ArrayList<>();
        List<Object[]>report = reservationCrudRepository.countTotalReservationsByClient();
        for(int i=0; i<report.size();i++){
            res.add(new CountClientes((Long)report.get(i)[1],(Client) report.get(i)[0]));
        }
        return res;

    }

}
