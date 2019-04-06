package YourTicket.controller;

import YourTicket.model.Event;
import YourTicket.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    /**
     * Aceasta metoda afiseaza toate evenimentele din baza de date
     * @return JSON de evenimete
     */
    @GetMapping("/all")
    public @ResponseBody List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    /**
     * Aceasta metoda afiseaza rezultatele unei cautari
     * Cautarea se face dupa numele si locatia evenimentului
     * @param text string-ul dupa care se face cautarea
     * @return JSON de evenimete
     */
    @GetMapping("/search/{text}")
    public @ResponseBody List<Event> searchEvents(@PathVariable("text") String text){
        String newText = "%" + text +  "%";
        return eventRepository.findByName(newText);
    }

    /**
     * Adagua un eveniment in baza de date
     * @param event noul eveniment
     * @return evenimentul adaugat
     */
    @PostMapping("/add")
    public @ResponseBody Event add(@RequestBody Event event){
        return eventRepository.save(event);
    }

    @DeleteMapping("/delete/{name}")
    public @ResponseBody boolean  delete(@PathVariable("name") String name){
        List<Event> events = this.getAllEvents();
        for(Event e:events)
            if(e.getName().equalsIgnoreCase(name)) {
                eventRepository.deleteById(e.getIdEvent());
                return true;
            }
        return false;
    }

    @PutMapping("/update/{id}")
    public @ResponseBody void update(@PathVariable("id") Integer id, @RequestBody Event event) {
        Event updateEvent = eventRepository.getOne(id);
        updateEvent.setLocation(event.getLocation());
        updateEvent.setDate(event.getDate());
        updateEvent.setDescription(event.getDescription());
        eventRepository.save(updateEvent);
    }

}
