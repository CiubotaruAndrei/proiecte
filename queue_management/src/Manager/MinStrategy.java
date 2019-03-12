package Manager;

import Coada.*;

import java.util.List;

public class MinStrategy implements Strategy {

    @Override
    public void addClient(List<Coada> cozi, Client c) {
        int min=cozi.get(0).list.size();
        int nr=0;
        int j=0;
        for (Coada i : cozi) {
            if(i.list.size()<min) {
                min=i.list.size();
                nr=j;
            }
            j++;
        }
        cozi.get(nr).addClient(c);
    }

}

