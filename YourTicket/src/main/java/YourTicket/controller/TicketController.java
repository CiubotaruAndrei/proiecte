package YourTicket.controller;


import YourTicket.model.NormalTicket;
import YourTicket.model.PresaleTicket;
import YourTicket.model.Ticket;
import YourTicket.model.Ticketfactory;
import YourTicket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;
    Ticketfactory ticketfactory = new Ticketfactory();

    @GetMapping("/all")
    public @ResponseBody List<Ticketfactory> getAllTickets(){
        return ticketRepository.findAll();
    }

    @PostMapping("/add")
    public @ResponseBody Ticketfactory addTicket(@RequestBody Ticketfactory t){
        return ticketRepository.save(t);
    }




}
