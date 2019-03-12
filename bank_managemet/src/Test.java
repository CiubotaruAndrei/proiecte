import sun.dc.path.PathError;

import static org.junit.Assert.*;

public class Test {

    Bank bank = new Bank();

    @org.junit.Test
    public void addTest(){
        Person p = new Person(123,"ionica","ro","0439593000");
        assertEquals(1,bank.addPerson(p));
        assertEquals(2,bank.addPerson(p));
    }

    @org.junit.Test
    public void removeTest() {
        Person p = new Person(123,"ionica","ro","0439593000");
        bank.addPerson(p);
        assertEquals(2,bank.removePerson(1223));//id invalid
        assertEquals(1,bank.removePerson(123));
    }

    @org.junit.Test
    public void accountTest() {
        Person p = new Person(123,"ionica","ro","0439593000");
        Account a1 = new SavingAccount(1,123,"SavingAccount",0,1.6f);
        Account a2 = new SpendingAccount(3,123,"SpendingAccount",0);
        Account a3 = new SpendingAccount(11,777,"SpendingAccount",0);
        bank.addPerson(p);
        assertEquals(1,bank.addAccount(a1));
        assertEquals(1,bank.addAccount(a2));
        assertEquals(3,bank.addAccount(a2)); //contul exista
        assertEquals(2,bank.addAccount(a3)); //persoana nu exista
        assertEquals(5,bank.withddraw(123,3,700)); //fonduri insuficiente
        assertEquals(1,bank.deposit(123,3,1000));
        assertEquals(1,bank.withddraw(123,3,700));
        assertEquals(300,bank.findAcc(123,3).getValoare(),0);
    }
}
