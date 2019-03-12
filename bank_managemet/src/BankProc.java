import java.util.List;

public interface BankProc {
    /**
     * @pre p!=null
     */
    int addPerson(Person p);

    int removePerson(int id);

    /**
     * @pre a!=null
     */
    int addAccount(Account a);

    int removeAccount(int personId, int id);

    List getPersons();

    List getAccounts();

    int editPerson(int id, String nume, String adresa, String telefon);

    /**
     * @pre valoare>0 && dobanda>0
     */
    int editAccount(int personId, int id, float valoare, float dobanda);

    /**
     * @pre sum>0
     */
    int deposit(int personId, int id, float sum);

    /**
     * @pre sum>0
     */
    int withddraw(int personId, int id, float sum);
}
