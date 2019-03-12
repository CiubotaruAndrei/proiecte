package Manager;

public class Time implements Runnable{
    private int time;
    private Thread thread;
    private int ok;
    public Time() {
        time=0;
        thread= new Thread(this);
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void start() {
        ok=1;
        thread.start();
    }

    public void stop(){
        ok=0;
    }

    @Override
    public void run() {
        while(ok==1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }

    }

}
