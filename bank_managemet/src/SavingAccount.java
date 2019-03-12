public class SavingAccount extends Account {
    private float dobanda;

    public SavingAccount(int id, int personId, String tip, float valoare, float dobanda) {
        super(id, personId, tip, valoare);
        this.dobanda = dobanda;
    }

    public float getDobanda() {
        return dobanda;
    }

    public void setDobanda(float dobanda) {
        this.dobanda = dobanda;
    }

    public String toString() {
        return super.toString() + " " + dobanda;
    }

    @Override
    public boolean deposit(float sum) {
        if(sum<0 || super.getValoare()>0)
            return false;
        super.setValoare(super.getValoare()+sum);
        setChanged();
        notifyObservers("depunere: " + sum);
        return true;
    }

    @Override
    public boolean withdraw(float sum) {
        if(super.getValoare()==0)
            return false;
        float s = super.getValoare();
        super.setValoare(0);
        setChanged();
        notifyObservers("retragere: " + (s+s*dobanda));
        return true;
    }

}
