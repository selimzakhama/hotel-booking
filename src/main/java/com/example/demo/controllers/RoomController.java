package com.example.demo.controllers;

import com.example.demo.Services.RoomService;
import com.example.demo.entities.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoomController {

    private final RoomService roomService;
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    /* HOME PAGE */
    @GetMapping("/home")
    public String getHome(Model model){
        List<Room> rooms = roomService.getAllRooms();

        model.addAttribute("myRooms",rooms);
        return "home";
    }

    @GetMapping("/AdminRooms")
    public String getRooms(Model model){

        Room room = new Room();
        model.addAttribute("room", room);

        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);

        return "adminDashboardRooms";
    }

    @PostMapping("/AdminRooms")
    @ResponseBody
    public ResponseEntity<String> newRoom(@RequestParam String roomName, @RequestParam int roomNumber, @RequestParam String roomType,
                                          @RequestParam int roomCapacity, @RequestParam String roomDescription, @RequestParam String roomImage){
        Room registredRoom = roomService.addRoom(roomName,roomNumber,roomType,roomCapacity,roomDescription,roomImage);
        if(registredRoom != null){
            //return "redirect:/AdminRooms";
            return ResponseEntity.ok("success");
        } else {
            //model.addAttribute("errorMsg","Room Number Already exists!");
            //return "AdminRooms";
            return ResponseEntity.ok("Room Number Already exists!");
        }
    }

    @PostMapping("/deleteRoom")
    @ResponseBody
    public ResponseEntity<String> deleteRoom(@RequestParam Long id, @RequestParam String action){
        Room deletedRoom = roomService.deleteRoom(id);
        if(deletedRoom != null){
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("Room not found");
        }
    }

    @PostMapping("/updateRoom")
    @ResponseBody
    public Room getRoom(@RequestParam Long id, @RequestParam String action){
        return roomService.getRoomById(id);
    }

    @PostMapping("/submitupdateRoom")
    @ResponseBody
    public ResponseEntity<String> updateRoom(@RequestParam Long id, @RequestParam String roomName, @RequestParam String roomType,
                                             @RequestParam int roomCapacity, @RequestParam String roomDescription, @RequestParam String roomImage) {
        Room roomToUpdate = roomService.getRoomById(id);

        if (roomToUpdate != null) {
            roomToUpdate.setRoomName(roomName);
            roomToUpdate.setRoomType(roomType);
            roomToUpdate.setRoomCapacity(roomCapacity);
            roomToUpdate.setRoomDescription(roomDescription);
            roomToUpdate.setRoomImage(roomImage);

            roomService.saveRoom(roomToUpdate);

            return ResponseEntity.ok("Room updated successfully");
        } else {
            return ResponseEntity.ok("Room not found");
        }
    }

}
