package YourTicket.model;

public class PresaleTicket implements TicketInterface{

    @Override
    public int setDiscount() {
        return 20;
    }

    @Override
    public String ticketType() {
        return "Presale";
    }

}
