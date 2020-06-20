package com.bootstudy.study;

import lombok.Data;

public interface RoomService {
    Room getRoom(String roomId);
    void insertRoom(Room room);

    @Data
    public static class Room {
        Long id;
        String name;
    }
}
