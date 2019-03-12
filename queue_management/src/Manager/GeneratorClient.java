package Manager;

import Coada.*;
import GUI.Gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GeneratorClient implements Runnable{
    private List<Coada> cozi;
    private int nrMaxCozi;
    private int minSos;
    private int maxSos;
    private int minSer;
    private int maxSer;
    private int durata;
    private Thread thread;
    private Strategy strategy;
    private Gui gui;
    private Time time;
    private int peakHour;


    public GeneratorClient(int nrMaxCozi,int minSos, int maxSos, int minSer, int maxSer, int durata, String strategie,Gui gui) {
        this.nrMaxCozi=nrMaxCozi;
        this.minSos=minSos;
        this.maxSos=maxSos;
        this.minSer=minSer;
        this.maxSer=maxSer;
        this.durata=durata;
        peakHour=0;
        if(strategie.equals("Random"))
            this.strategy=new RandomStrategy();
        else if(strategie.equals("Min_Clienti"))
            this.strategy=new MinStrategy();
        else if(strategie.equals("Min_Servire"))
            this.strategy=new MinWaitingStrategy();
        cozi= Collections.synchronizedList(new ArrayList<Coada>());
        time=new Time();
        int i;
        for(i=1;i<=nrMaxCozi;i++) {
            Coada coada=new Coada(i,time,gui);
            cozi.add(coada);
        }
        this.gui=gui;
        thread = new Thread(this);
        thread.start();
    }

        public int nrClienti() {
            int nr_total=0;
            for(Coada i: cozi) {
                nr_total+=i.list.size();
            }
            return nr_total;
        }

    public void run() {
        Random rand = new Random();
        int max=0;
        int id=0;
        time.start();
        while(time.getTime()<durata) {
            int sos=minSos + rand.nextInt(maxSos-minSos+1);
            int ser=minSer + rand.nextInt(maxSer-minSer+1);
            if(time.getTime()+sos>durata)
                break;
            id++;
            System.out.printf("time: %d\n", time.getTime());
            try {
                Thread.sleep(sos*500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Client c=new Client(sos,ser,id);
            strategy.addClient(this.cozi,c);
            if(max<nrClienti()){
                max=nrClienti();
                peakHour=time.getTime();
            }
        }
        gui.actualizare("STOP");
        for (Coada i: cozi)
            i.threadStop();
        time.stop();
        gui.actualizare("Peak Hour: " + peakHour);
    }

}
