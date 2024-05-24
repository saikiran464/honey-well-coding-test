package com.honeywell;

import com.honeywell.objects.Seat;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat) {
        if (!seat.isBooked()) {
            seat.setBooked(true);
            seats.add(seat);
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }
}

