package com.example.SandBox.webservice;

import com.example.SandBox.business.ReservationService;
import com.example.SandBox.business.RoomReservation;
import com.example.SandBox.data.Guest;
import com.example.SandBox.data.Room;
import com.example.SandBox.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//Request controller by default returns a JSON body instead of raw HTML (This is springboot functionality)
@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }
    // /api/reservations nesting response
    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false)String dateString){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }


    @GetMapping("/guests")
    public List<Guest> getGuests(){
        return this.reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }
    @GetMapping("/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getRooms();
    }
}
