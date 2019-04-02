package YourTicket.model;


public class NormalTicket implements Ticket{

    private Integer idTicket;
    private Integer idEvent;
    private Integer price;
    private Integer quantity;

    public NormalTicket(Integer idTicket, Integer idEvent, Integer price, Integer quantity) {
        this.idTicket = idTicket;
        this.idEvent = idEvent;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String ticketType() {
        return "Normal";
    }
}
