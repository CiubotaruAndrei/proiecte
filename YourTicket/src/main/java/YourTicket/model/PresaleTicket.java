package YourTicket.model;

public class PresaleTicket implements Ticket{

    private Integer idTicket;
    private Integer idEvent;
    private Integer price;
    private Integer quantity;
    private Integer discount;

    public PresaleTicket(Integer idTicket, Integer idEvent, Integer price, Integer quantity, Integer discount) {
        this.idTicket = idTicket;
        this.idEvent = idEvent;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    @Override
    public String ticketType() {
        return "Presale";
    }

}
