package com.example.SandBox.web;


import com.example.SandBox.business.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guests")
public class GuestController {
    //Define singleton
    private final ReservationService reservationService;

    //Construct service
    public GuestController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    //Responds to GET method, calls "getGuests" with a model to get the template view
    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model){
        model.addAttribute("guests", this.reservationService.getHotelGuests());
        return "hotel-guests";
    }
}
