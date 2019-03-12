public class SpendingAccount extends Account {

    public SpendingAccount(int id, int personId, String tip, float valoare) {
        super(id, personId, tip, valoare);
    }

    @Override
    public boolean deposit(float sum) {
        if(sum<0)
            return false;
        super.setValoare(super.getValoare()+sum);
        setChanged();
        notifyObservers("depunere: " + sum);
        return true;
    }

    @Override
    public boolean withdraw(float sum) {
        if(super.getValoare()-sum<0)
            return false;
        super.setValoare(super.getValoare()-sum);
        setChanged();
        notifyObservers("retragere: " + sum);
        return true;
    }
}
