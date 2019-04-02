package YourTicket.model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class Ticketfactory {

    @Id
    private Integer idTicket;
    private Integer idEvent;
    private Integer price;
    private Integer quantity;
    private Integer discount;


    public Ticket getTicket(String type, int idTicket, int idEvent, int price, int quantity, int discount){
        if(type.equalsIgnoreCase("NORMAL"))//normal ticket
            return new NormalTicket(idTicket, idEvent,price,quantity);
        else if(type.equalsIgnoreCase("PRESALE"))//presale ticket
            return new PresaleTicket(idTicket, idEvent,price,quantity,discount);
        return null;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
