import java.io.*;
import java.util.*;

public class Bank implements BankProc{

    private HashMap<Person,List<Account>> date;

    public Bank() {
        date=(HashMap<Person,List<Account>>)deserialzation();
        if(date==null)
            date=new HashMap<>();
        for (Map.Entry<Person, List<Account>> entry : date.entrySet()) {
            for (Account a : entry.getValue())
                a.addObserver(entry.getKey());
        }
    }

    @Override
    public int addPerson(Person p) {
        assert p!=null;
        List<Account> accounts = new ArrayList<>();
        Person aux = findById(p.getId());
        if(aux!=null)
            return 2;//persoana exista
        date.put(p,accounts);
        return 1;
    }

    @Override
    public int removePerson(int id) {
        Person delP = new Person();
        int err=0;
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==id) {
                delP = entry.getKey();
                err = 1;
            }
        }
        if(err==0)
            return 2;//persoana nu exista
        date.remove(delP);
        return 1;//succes
    }

    @Override
    public int addAccount(Account a) {
        assert a!=null;
        if(findById(a.getPersonId())==null)
            return 2; //persoana nu exista
        if(findAcc(a.getPersonId(),a.getId())!=null)
            return 3; //contul exista
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==a.getPersonId()) {
                entry.getValue().add(a);
                a.addObserver(entry.getKey());
            }
        }
        return 1;
    }

    @Override
    public int removeAccount(int personId, int id) {
        List<Account> accounts = new ArrayList<>();
        Account delA = null;
        Person p = new Person();
        int err=0;
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==personId) {
                accounts.addAll(entry.getValue());
                p=entry.getKey();
                err = 1;
            }
        }
        if(err==0)
            return 2;//persoana nu exista
        for(Account a : accounts)
            if(a.getId()==id) {
                delA=a;
                err=2;
            }

        if(err==1)
            return 3;//contul nu exista
        accounts.remove(delA);
        date.put(p,accounts);
        return 1;//succes
    }

    @Override
    public List getPersons() {
        List<Person> persons = new ArrayList<>();
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            persons.add(entry.getKey());
        }
        return persons;
    }

    @Override
    public List getAccounts() {
        List<Account> accounts = new ArrayList<>();
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            accounts.addAll(entry.getValue());
        }
        return accounts;
    }

    @Override
    public int editAccount(int personId, int id, float valoare, float dobanda) {
        assert valoare>=0 && dobanda>=0;
        List<Account> accounts = new ArrayList<>();
        Person p = new Person();
        int err=0;
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==personId) {
                err = 1;
                accounts.addAll(entry.getValue());
                p=entry.getKey();
            }
        }
        if(err==0)
            return 2;//persoana nu exista
        for(Account a : accounts) {
            if (a.getId() == id) {
                if (valoare > 0)
                    a.setValoare(valoare);
                if (a instanceof  SavingAccount && dobanda > 0)
                    ((SavingAccount) a).setDobanda(dobanda);
                err = 2;
            }
        }

        if(err==1)
            return 3;//contul nu exista
        date.put(p,accounts);
        return 1;//succes
    }

    @Override
    public int editPerson(int id, String nume, String adresa, String telefon) {
        int err=0;
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==id) {
                if(!nume.equals(""))
                    entry.getKey().setNume(nume);
                if(!adresa.equals(""))
                    entry.getKey().setAdresa(adresa);
                if(!telefon.equals(""))
                    entry.getKey().setTelefon(telefon);
                err = 1;
            }
        }
        if(err==0)
            return 2;//clientul nu exista
        return 1;//succes
    }

    public void serialization() {

        try {
            FileOutputStream file = new FileOutputStream("file.ser");
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(date);
            obj.close();
            file.close();
            System.out.printf("Serialized HashMap data is saved in file.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HashMap deserialzation() {

        HashMap date = null;
        try {
            FileInputStream file = new FileInputStream("file.ser");
            ObjectInputStream obj = new ObjectInputStream(file);
            date = (HashMap)obj.readObject();
            obj.close();
            file.close();
            System.out.println("Deserialized HashMap data from file.ser");
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public int deposit(int personId, int id, float sum) {
        assert sum>0;
        List<Account> accounts = new ArrayList<>();
        Person p = new Person();
        int err=0;
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==personId) {
                err = 1;
                accounts.addAll(entry.getValue());
                p=entry.getKey();
            }
        }
        if(err==0)
            return 2;//persoana nu exista
        for(Account a : accounts) {
            if (a.getId() == id) {
                if(!a.deposit(sum))
                    return 4;// o singura depunere
                err = 2;
            }
        }

        if(err==1)
            return 3;//contul nu exista
        date.put(p,accounts);
        return 1;//succes
    }

    @Override
    public int withddraw(int personId, int id, float sum) {
        assert sum>0;
        List<Account> accounts = new ArrayList<>();
        Person p = new Person();
        int err=0;
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==personId) {
                err = 1;
                accounts.addAll(entry.getValue());
                p=entry.getKey();
            }
        }
        if(err==0)
            return 2;//persoana nu exista
        for(Account a : accounts) {
            if (a.getId() == id) {
                if(!a.withdraw(sum))
                    return 5;//fonduri insuficiente
                err = 2;
            }
        }

        if(err==1)
            return 3;//contul nu exista
        date.put(p,accounts);
        return 1;//succes
    }

    public Person findById(int id) {
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==id)
                return entry.getKey();
        }
        return null;
    }

    public Account findAcc(int personId, int id) {
        for(Map.Entry<Person,List<Account>> entry : date.entrySet()) {
            if(entry.getKey().getId()==personId)
                for(Account a : entry.getValue())
                    if(a.getId()==id)
                        return a;
        }
        return null;
    }

}
