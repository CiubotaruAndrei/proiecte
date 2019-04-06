package YourTicket.controller;


import YourTicket.model.Ticket;
import YourTicket.model.TicketFactory;
import YourTicket.model.TicketInterface;
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
    TicketFactory ticketFactory = new TicketFactory();

    @GetMapping("/all")
    public @ResponseBody List<Ticket> finaAll(){
        return ticketRepository.findAll();
    }

    @PostMapping("/add")
    public @ResponseBody Ticket add(@RequestBody Ticket ticket){
        TicketInterface type = ticketFactory.getTicket(ticket.getType());
        ticket.setDiscount(type.setDiscount());
        return ticketRepository.save(ticket);
    }

    @DeleteMapping("/delete/{id_event}/{id_ticket}")
    public @ResponseBody boolean delete(@PathVariable("id_event") Integer idEvent, @PathVariable("id_ticket") Integer idTicket) {
        List<Ticket> tickets = this.finaAll();
        for(Ticket t: tickets)
            if(t.getIdEvent().equals(idEvent) && t.getIdTicket().equals(idTicket))
            {
                ticketRepository.delete(t);
                return true;
            }

         return false;
    }




}
