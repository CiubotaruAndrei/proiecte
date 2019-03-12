package Manager;

import Coada.*;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {

    @Override
    public void addClient(List<Coada> cozi, Client c) {
        int nrCozi = cozi.size();
        Random rand = new Random();
        int nr = rand.nextInt(nrCozi);
        cozi.get(nr).addClient(c);
    }
}
