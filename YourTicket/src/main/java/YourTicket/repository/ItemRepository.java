package YourTicket.repository;

import YourTicket.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Modifying
    @Transactional
    @Query("update Ticket t set t.quantity = t.quantity - :quantity where t.idTicket = :idTicket")
    void updateTicketQuantity(@Param("idTicket") Integer idTicket, @Param("quantity") Integer quantity);
}
