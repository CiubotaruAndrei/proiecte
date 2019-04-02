package YourTicket.repository;


import YourTicket.model.Ticketfactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticketfactory,Integer> {
}
