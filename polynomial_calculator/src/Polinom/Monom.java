package Polinom;

public class Monom implements Comparable{
    private float coef;
    private int pow;

    public Monom(float coef, int pow) {
        this.coef = coef;
        this.pow = pow;
    }

    public float getCoef() {
        return coef;
    }

    public int getPow() {
        return pow;
    }

    public String afisare() {
        if(pow!=0) {
            if (coef > 0)
                return "+" + coef + "X^" + pow;
            else if (coef == 0)
                return "";
            else
                return coef + "X^" + pow;
        }
        else {
            if (coef >= 0)
                return "+" + coef;
            else
                return "" + coef;
        }
    }

    public void setCoef(float coef) {
        this.coef = coef;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }

    @Override
    public int compareTo(Object o) {
        Monom a = (Monom)o;
        return Integer.compare(a.pow,pow);
    }
}
