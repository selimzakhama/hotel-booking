package com.example.demo.Services;

import com.example.demo.entities.Reservation;
import com.example.demo.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }



    /* CREATE RESERVATION */
    public Reservation formAdd(String name, String email, String phone_number, int nb_adults,
                               int nb_children, String check_in_date, String check_out_date, String room_preference,
                               String comments){

        String state = "demand";
        Reservation form = new Reservation();
        form.setName(name);
        form.setEmail(email);
        form.setPhone_number(phone_number);
        form.setNb_adults(nb_adults);
        form.setNb_children(nb_children);
        form.setCheck_in_date(check_in_date);
        form.setCheck_out_date(check_out_date);
        form.setRoom_preference(room_preference);
        form.setComments(comments);
        form.setState(state);

        return reservationRepository.save(form);
    }

    /* GET ALL RESERVATIONS */
    public List<Reservation> getReservationsByState(String state){
        return reservationRepository.findByStateOrderByIdDesc(state);
    }
    public Optional<Reservation> getReservationById(Long Id){
        return reservationRepository.findById(Id);
    }
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
