package businessLogic.validators;

import model.Produs;

import java.util.regex.Pattern;

public class ProdusValidator {
    /**
     * valideaza atribute
     * @param produs obiect de tipul Produs
     * @return anumite coduri de eroare
     */
    public int validate(Produs produs) {
        String denumire_pattern = "^[a-zA-Z0-9\\s]*$";
        int ok = 0;
        Pattern denumire = Pattern.compile(denumire_pattern);
        System.out.println(produs.getDenumire());
        System.out.println(denumire.matcher(produs.getDenumire()).matches());
        if(!denumire.matcher(produs.getDenumire()).matches() || produs.getDenumire().equals(""))
            return 2; //denumire invalida
        if(produs.getPret()<=0)
            return 3; //pret invalid
        if(produs.getStoc()<=0)
            return 4; //stoc invalid
        return ok;
    }
}
