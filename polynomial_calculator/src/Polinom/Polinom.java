package Polinom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Polinom {
    private List<Monom> monoame = new ArrayList<>();

    public Polinom() {}

    public int verificarePol(String polinom){
        String caractere="0123456789+-X^";
        int i,ok=1;
        //verifica daca string-ul contine caractere nedorite
        for(i=0; i<polinom.length(); i++)
            if (caractere.indexOf(polinom.charAt(i))==-1)
                ok=0;
        String polinomRegex = "(-|\\+){2,}|(\\d+\\^)|(\\+-)|(-\\+)|(X\\d+)|(\\^X)|(XX+)|" +
                "(^X\\^+$)|(^\\^\\d*$)|(^(\\+|-)?(X|\\^)?(\\+|-)$)";//regex pentru cazuri rele
        Pattern pattern = Pattern.compile(polinomRegex);
        Matcher matcher = pattern.matcher(polinom);
        if(matcher.find() || polinom.equals(""))//daca exista match polinomul nu e bun
            ok=0;
        System.out.println(ok);
        return ok;
    }

    public int construire(String polinom) {
        int pow, coef, ok;
        String polinomRegex = "(\\+|-)?(\\d*)(X)?\\^?(\\d*)";//regex pentru determinarea monoamelor
        Pattern pattern = Pattern.compile(polinomRegex);
        Matcher matcher = pattern.matcher(polinom);
        if (this.verificarePol(polinom)==1) {
            while (matcher.find()) {
                ok = 1;
                String monom = matcher.group(0);//monomul
                String semn = matcher.group(1);//semnul
                String group2 = matcher.group(2);//coeficientul
                String x = matcher.group(3);//X
                String group4 = matcher.group(4);//gradul
                System.out.println(monom + ":" + semn + ", " + group2 + ", " + x + ", " + group4);
                if (monom.equals(""))
                    ok = 0;
                if (ok == 1) {
                    if (group2.equals(""))
                        coef = 1;
                    else
                        coef = Integer.parseInt(group2);
                    if (semn != null)
                        if (semn.equals("-"))
                            coef = -coef;
                    if (x == null)
                        pow = 0;
                    else if (group4.equals(""))
                        pow = 1;
                    else
                        pow = Integer.parseInt(group4);
                    System.out.printf("%d , %d \n", coef, pow);

                    Monom p = new Monom(coef, pow);//creez un nou monom si il adaug in polinom
                    this.addMonom(p);
                }
            }
            return 1;
        }
        else return 0;
    }


    public void addMonom(Monom a) {
        int ok = 0;
        for (Monom i : monoame) {
            if (a.getPow() == i.getPow()) {
                i.setCoef(i.getCoef() + a.getCoef());
                ok = 1;
            }
        }
        if (ok == 0)
            monoame.add(a);
    }

    public String afisarePolinom() {
        String a = "";
        if(monoame.size()==1 && monoame.get(0).getCoef()==0)
            a = a + "0.0";
        else {
            for (Monom i : monoame)
                if(i.getCoef()==0 && i.getPow()==0)
                    a = a + "";
                else
                    a = a + i.afisare();
        }
        return a;
    }

    public void sortare() {
        Collections.sort(monoame);
    }

    public void adunare(Polinom b) {
        for (Monom i : monoame) {
            for (Monom j : b.monoame)
                if (i.getPow() == j.getPow()) {
                    i.setCoef(i.getCoef() + j.getCoef());
                }
        }
        int ok;
        for (Monom j : b.monoame) {
            ok = 0;
            for (Monom i : monoame) {
                if (j.getPow() == i.getPow())
                    ok = 1;
            }
            if (ok == 0)
                monoame.add(new Monom(j.getCoef(), j.getPow()));
        }
        this.sortare();
    }

    public void scadere(Polinom b) {
        for (Monom i : monoame) {
            for (Monom j : b.monoame)
                if (i.getPow() == j.getPow()) {
                    i.setCoef(i.getCoef() - j.getCoef());
                }
        }
        int ok;
        for (Monom j : b.monoame) {
            ok = 0;
            for (Monom i : monoame) {
                if (j.getPow() == i.getPow())
                    ok = 1;
            }
            if (ok == 0)
                monoame.add(new Monom((-1) * j.getCoef(), j.getPow()));
        }
        this.sortare();
    }

    public void derivare() {
        for(Monom i : monoame){
            if(i.getPow()!=0) {
                i.setCoef(i.getCoef()*i.getPow());
                i.setPow(i.getPow()-1);
            }
            else
                i.setCoef(0);
        }
        this.sortare();
    }

    public void integrare() {
        for(Monom i : monoame) {
            if(i.getPow()!=0) {
                i.setCoef(i.getCoef()/(i.getPow()+1));
                i.setPow(i.getPow()+1);
            }
            else
                i.setPow(1);
        }
        this.sortare();
    }

    public void inmultire(Polinom b) {
        Polinom c = new Polinom();
        for(Monom i : monoame) {
            for(Monom j : b.monoame)
                c.addMonom(new Monom(i.getCoef()*j.getCoef(),i.getPow()+j.getPow()));
        }
        monoame.removeAll(monoame);
        for(Monom i: c.monoame){
            monoame.add(new Monom(i.getCoef(),i.getPow()));
        }
        this.sortare();
    }
}
