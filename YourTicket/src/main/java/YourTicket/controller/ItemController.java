package YourTicket.controller;

import YourTicket.model.Item;
import YourTicket.model.Ticket;
import YourTicket.repository.ItemRepository;
import YourTicket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;


    @PostMapping("/add")
    public @ResponseBody Item addTicket(@RequestBody Item item){
        //update cantitate ticket
        itemRepository.updateTicketQuantity(item.getIdTicket(), item.getQuantity());
        return itemRepository.save(item);
    }

    @DeleteMapping("/delete")
    public @ResponseBody void removeTicket(@RequestBody Item item){

        itemRepository.updateTicketQuantity(item.getIdTicket(),-item.getQuantity());
        itemRepository.delete(item);
    }
}
