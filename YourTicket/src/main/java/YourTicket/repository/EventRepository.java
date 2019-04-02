package YourTicket.repository;

import YourTicket.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {

    @Query("select e from Event e where e.name like :text or e.location like :text")
    List<Event> findByName(@Param("text") String text);
}

