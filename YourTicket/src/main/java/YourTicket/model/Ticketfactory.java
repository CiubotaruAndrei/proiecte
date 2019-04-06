package YourTicket.model;

public class TicketFactory {

    public TicketInterface getTicket(String type)
    {
        if(type.equalsIgnoreCase("NORMAL"))
            return new NormalTicket();
        else if(type.equalsIgnoreCase("PRESALE"))
            return new PresaleTicket();

        return null;
    }
}
