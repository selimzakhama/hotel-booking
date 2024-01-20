package com.example.demo.controllers;

import com.example.demo.Services.ReservationService;
import com.example.demo.entities.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private final ReservationService reservationService;
    public AdminController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/adminDashboard")
    public String getDashboard(Model model){
        List<Reservation> reservations = reservationService.getReservationsByState("demand");
        List<Reservation> confirmedReservations = reservationService.getReservationsByState("confirmed");
        model.addAttribute("reservations",reservations);
        model.addAttribute("CReservations",confirmedReservations);
        return "adminDashboard";
    }


    @PostMapping("/adminDashboard")
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
