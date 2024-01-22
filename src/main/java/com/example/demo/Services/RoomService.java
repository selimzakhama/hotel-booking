package com.example.demo.Services;

import com.example.demo.entities.Room;
import com.example.demo.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public Room addRoom(String roomName, int roomNumber,String roomType,int roomCapacity,String roomDescription,
                        String roomImage){
        if(roomRepository.findRoomByRoomNumber(roomNumber).isEmpty()){
            Room room = new Room();
            room.setRoomName(roomName);
            room.setRoomNumber(roomNumber);
            room.setRoomCapacity(roomCapacity);
            room.setRoomType(roomType);
            room.setRoomDescription(roomDescription);
            room.setRoomImage(roomImage);
            return roomRepository.save(room);
        } else {
            return null;
        }
    }
    public void saveRoom(Room room){
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id){
        Optional<Room> roomOptional = roomRepository.findById(id);
        return roomOptional.orElse(null);
    }

    public Room deleteRoom(Long id){
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            Room roomToDelete = roomOptional.get();
            roomRepository.deleteById(id);
            return roomToDelete;
        } else {
            return null;
        }
    }

}
