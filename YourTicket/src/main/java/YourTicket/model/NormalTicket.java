package YourTicket.model;


public class NormalTicket implements TicketInterface{

    @Override
    public int setDiscount() {
        return 0;
    }

    @Override
    public String ticketType() {
        return "Normal";
    }


}
