package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
    private int roomNumber;
    private String roomType;
    private int roomCapacity;
    private String roomDescription;
    private String roomImage;

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }
}
