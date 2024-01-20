package com.example.demo.controllers;


import com.example.demo.Services.ReservationService;
import com.example.demo.entities.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String getForm(Model model){
        Reservation reservation = new Reservation();
        model.addAttribute("myReservation",reservation);
        return "reservation";
    }

    @PostMapping("/reservation")
    public String addForm(@ModelAttribute("myReservation") Reservation reservation, Model model){
        Reservation registredReservation = reservationService.formAdd(reservation.getName(),reservation.getEmail(),
                reservation.getPhone_number(),reservation.getNb_adults(),reservation.getNb_children(),
                reservation.getCheck_in_date(),reservation.getCheck_out_date(),reservation.getRoom_preference(),
                reservation.getComments());
        if(registredReservation == null ){
            model.addAttribute("errorMsg", "Error inserting form");
            return "reservation";
        } else {
            model.addAttribute("succMsg", "Reservation demanded Successfully!");
            return "reservation";
        }

    }

    @GetMapping("/liste_reservation")
    public String display(Model model){
        List<Reservation> reservations = reservationService.getReservationsByState("demand");
        model.addAttribute("reservations",reservations);
        return "liste_reservation";
    }

    @PostMapping("/liste_reservation")
    public ResponseEntity<String> updateReservationState(@RequestParam Long id, @RequestParam String action){
        Optional<Reservation> selectedReservation = reservationService.getReservationById(id);
        if(selectedReservation.isPresent()){
            Reservation reservation = selectedReservation.get();
            if ("confirm".equals(action)) {
                reservation.setState("confirmed");
                reservationService.saveReservation(reservation);
                return ResponseEntity.ok("Reservation confirmed successfully");
            } else {
                reservation.setState("refused");
                reservationService.saveReservation(reservation);
                return ResponseEntity.ok("Reservation cancelled!");
            }
        } else {
            return ResponseEntity.ok("Reservation not found");
        }
    }


}
