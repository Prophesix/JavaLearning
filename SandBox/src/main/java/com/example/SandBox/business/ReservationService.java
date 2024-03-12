package com.example.SandBox.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.SandBox.data.Guest;
import com.example.SandBox.data.GuestRepository;
import com.example.SandBox.data.Reservation;
import com.example.SandBox.data.ReservationRepository;
import com.example.SandBox.data.Room;
import com.example.SandBox.data.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    //Singletons (best practice)
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    //Singleton Constructor (best practice)
    //If you define multi constructors you must @Autowire otherwise Spring will not know how to construct this class
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    //Gets room Reservation based on a provided Date
    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getResId()).get();
            roomReservation.setFirstName(guest.getfName());
            roomReservation.setLastName(guest.getlName());
            roomReservation.setRoomId(guest.getId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomname().equals(o2.getRoomname())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomname().compareTo(o2.getRoomname());
            }
        });
        return roomReservations;
    }
}