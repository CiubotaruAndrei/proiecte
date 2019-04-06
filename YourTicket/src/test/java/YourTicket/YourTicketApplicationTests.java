package YourTicket;

import YourTicket.model.TicketFactory;
import YourTicket.model.TicketInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YourTicketApplicationTests {

	@Mock
	TicketInterface ticket;
	private TicketFactory ticketFactory;

	@Before
	public void init() {
		ticketFactory = new TicketFactory();
	}

	@Test
	public void tiketTypeNormal() {
		ticket = ticketFactory.getTicket("normal");
		assertEquals("Normal", ticket.ticketType());
		assertEquals(0,ticket.setDiscount());
	}

	@Test
	public void ticketTypePresale() {
		ticket = ticketFactory.getTicket("presale");
		assertEquals("Presale",ticket.ticketType());
		assertEquals(20,ticket.setDiscount());
	}

}
