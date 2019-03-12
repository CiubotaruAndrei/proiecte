package businessLogic.validators;

import model.Client;

import java.util.regex.Pattern;

public class ClientValidator {
    /**
     * valideaza atribute
     * @param client obiect de tipul Client
     * @return anumite coduri de eroare
     */
    public int validate(Client client) {
        String nume_pattern = "^[a-zA-Z\\s]*$";
        String telefon_pattern = "([0-9]){10}";
        String email_pattern = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}\\b";
        int ok = 0;
        Pattern nume = Pattern.compile(nume_pattern);
        Pattern telefon = Pattern.compile(telefon_pattern);
        Pattern email = Pattern.compile(email_pattern);
        if (!nume.matcher(client.getNume()).matches() || client.getNume().equals(""))
            return 2; //nume gresit
        if (!nume.matcher(client.getAdresa()).matches() || client.getAdresa().equals(""))
            return 3; //adresa gresita
        if (!telefon.matcher(client.getTelefon()).matches())
            return 4; //telefon gresit
        if (!email.matcher(client.getEmail()).matches())
            return 5; //email gresit
        return ok;
    }
}
