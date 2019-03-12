package Coada;

import GUI.Gui;
import Manager.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Coada implements Runnable {
    public List<Client> list;
    private int nrClienti;
    private int nrCoada;
    private int timp;
    private Thread thread;
    private Gui gui;
    private Time time;
    private boolean ok;
    private float timpAsteptareTotal;
    private float timpServire;
    private int coadaGoala;
    private int nrClientiSer;

    public Coada(int nr, Time time, Gui gui) {
        list= Collections.synchronizedList(new ArrayList<Client>());
        nrCoada=nr;
        nrClientiSer=0;
        timpAsteptareTotal=0;
        coadaGoala=0;
        timpServire=0;
        this.gui=gui;
        timp=0;
        this.time=time;
        ok=true;
        thread= new Thread(this);
        thread.start();
    }

    public int getNrCoada() {
        return nrCoada;
    }

    public void addClient(Client c) {
        list.add(c);
        nrClienti++;
        gui.actualizare("->Client id " + c.getId() + " sosit la coada " + nrCoada + " la timpul " + time.getTime() +
                     "          timp sosire: " + c.getTimpSosire() +  " timp servire: " + c.getTimpServire());
        gui.print(this);
    }

    @Override
    public void run() {
        int lastSevTime=0;
        int id=0;
        int timpAsteptare=0;
        while(ok || list.size()>0) {
            //System.out.println("time coada" + nrCoada+ ": "+ timp);
            if(list.size()!=0) {
                timp+=list.get(0).getTimpServire();
                if(lastSevTime-list.get(list.size()-1).getTimpSosire()>0) {
                    timpAsteptare += (lastSevTime - list.get(list.size() - 1).getTimpSosire());
                    timpAsteptareTotal += timpAsteptare;
                }
                else
                    timpAsteptare=0;
                //System.out.println("Timp asteptare: " + timpAsteptare);
                lastSevTime=list.get(list.size()-1).getTimpServire();
                //System.out.println("last ser time: "+ lastSevTime);
                try {
                    Thread.sleep(list.get(0).getTimpServire() * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timpServire+=list.get(0).getTimpServire();
                nrClientiSer++;
                id=list.get(0).getId();
                list.remove(0);
                gui.actualizare("<-Client id " + id + " iesit de la coada " + nrCoada + " la timpul " + timp);
                gui.print(this);
            }
            else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timp=time.getTime();
                coadaGoala++;
            }
        }
    }

    public void threadStop() {
        ok=false;
        thread.stop();
        gui.actualizare("Coada " + nrCoada + ":\n" + "Average service time: " + timpServire/nrClientiSer +
                " Average waiting time: " + timpAsteptareTotal/nrClientiSer + " Empty queue: " + (coadaGoala-1));
    }

    public float getTimpServire() {
        return timpServire;
    }

}
