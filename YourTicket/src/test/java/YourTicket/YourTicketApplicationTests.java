package YourTicket;

import YourTicket.controller.EventController;
import YourTicket.model.Event;
import YourTicket.model.Ticket;

import YourTicket.model.Ticketfactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YourTicketApplicationTests {

	@Mock
	Ticket ticket;
	private Ticketfactory ticketFactory;

	@Before
	public void init() {
		ticketFactory = new Ticketfactory();
	}

	@Test
	public void tiketTypeNormal() {
		ticket = ticketFactory.getTicket("normal", 2, 4, 25, 100, 10);
		assertEquals("Normal", ticket.ticketType());
	}

	@Test
	public void ticketTypePresale() {
		ticket=ticketFactory.getTicket("PRESALE",2,2,70,100,10);
		assertEquals("Presale",ticket.ticketType());
	}

}
