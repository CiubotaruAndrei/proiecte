package Manager;

import Coada.*;

import java.util.List;

public class MinWaitingStrategy implements Strategy {
    @Override
    public void addClient(List<Coada> cozi, Client c) {
        float min=cozi.get(0).getTimpServire();
        int nr=0;
        int j=0;
        for (Coada i : cozi) {
            if(i.getTimpServire()<min) {
                min=i.getTimpServire();
                nr=j;
            }
            j++;
        }
        cozi.get(nr).addClient(c);
    }
}
