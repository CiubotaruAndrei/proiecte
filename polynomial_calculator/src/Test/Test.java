package Test;

import static org.junit.Assert.*;

import Polinom.Polinom;

public class Test {
    Polinom polinom,a,b,c;
    @org.junit.Test
    public void testInput(){
        polinom = new Polinom();
        assertEquals(polinom.verificarePol("3X^4+2"),1);
        assertEquals(polinom.verificarePol("3"),1);
        assertEquals(polinom.verificarePol("X"),1);
        assertEquals(polinom.verificarePol("X^4"),1);
        assertEquals(polinom.verificarePol("-X^4+2"),1);
        assertEquals(polinom.verificarePol("3X^4dsa+2"),0);
        assertEquals(polinom.verificarePol("3X^4%+2"),0);
        assertEquals(polinom.verificarePol("3X^4-+2"),0);
        assertEquals(polinom.verificarePol("2^4+2X+1"),0);
        assertEquals(polinom.verificarePol("3X4+2"),0);
        assertEquals(polinom.verificarePol("-"),0);
        assertEquals(polinom.verificarePol("+"),0);
        assertEquals(polinom.verificarePol("X^^"),0);
    }

    @org.junit.Test
    public void testAdunare(){
        a = new Polinom();
        b = new Polinom();
        c = new Polinom();
        a.construire("3X^4+2X-5");
        b.construire("X^2+7X-3");
        c.construire("3X^4+X^2+9X-8");
        a.adunare(b);
        assertEquals(a.afisarePolinom(),c.afisarePolinom());
    }

    @org.junit.Test
    public void testScadere(){
        a = new Polinom();
        b = new Polinom();
        c = new Polinom();
        a.construire("3X^4+2X-5");
        b.construire("X^4+X^2+7X-3");
        c.construire("2X^4-X^2-5X-2");
        a.scadere(b);
        assertEquals(a.afisarePolinom(),c.afisarePolinom());
    }

    @org.junit.Test
    public void testDerivare() {
        a = new Polinom();
        b = new Polinom();
        a.construire("3X^4+7X+X^2-3X");
        b.construire("12X^3+2X+4");
        a.derivare();
        assertEquals(a.afisarePolinom(),b.afisarePolinom());
    }

    @org.junit.Test
    public void testIntegrare() {
        a = new Polinom();
        b = new Polinom();
        a.construire("5X^4+7X+3X^2-3X-3");
        b.construire("X^5+X^3+2X^2-3X");
        a.integrare();
        assertEquals(a.afisarePolinom(),b.afisarePolinom());
    }

}
