package bank;

import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;


public class PrivateBank implements Bank, Serializable {

   private String name;

   private double incomingInterest;

   private double outgoingInterest;

           /**
            * Dieses Attribut soll Konten mit Transaktionen verknüpfen,
            * so dass jedem gespeicherten Konto 0 bis n Transaktionen zugeordnet werden können.
            */
           public Map<String, List<Transaction>> accountsToTransactions=new HashMap<String, List<Transaction>>();

    /**
     *
     * @param name Name der privaten Bank setzen
     */
    void set_name (String name){this.name = name;}

    /**
     *
     * @return Name der privaten Bank
     */
    String get_name (){return this.name;}
    /**
     *
     * @param in "die Zinse an, die bei einer Einzahlung anfallen" setzen
     */
    void set_incomingInterest(double in){this.incomingInterest = in;}

    /**
     *
     * @return die Zinse an, die bei einer Einzahlung anfallen.
     */
    double get_incomingInterest(){ return this.incomingInterest;}

    /**
     *
     * @param out" die Zinse an, die bei einer Auszahlung anfallen." setzen
     */
    void set_outgoingInterest(double out){this.outgoingInterest = out;}

    /**
     *
     * @return  die Zinse an, die bei einer Auszahlung anfallen.
     */
    double get_outgoingInterest(){ return this.outgoingInterest;}

    /**
     * Konstuktor
     * @param Name  Name der privaten Bank
     * @param IncomingInt die Zinse an, die bei einer Einzahlung anfallen.
     * @param OutgoingInt die Zinse an, die bei einer Auszahlung anfallen.
     */

    public PrivateBank (String Name,double IncomingInt, double OutgoingInt ){
        this.name = Name;
        this.incomingInterest = IncomingInt;
        this.outgoingInterest = OutgoingInt;
    }

    /**
     * Copy-Konstruktor
     * @param P Objekt von Typ PrivateBank, das seine Attribute kopiert werden sollen.
     */
    public PrivateBank (PrivateBank P){
        this(P.name, P.incomingInterest, P.outgoingInterest);
    }
    /**
     *
     * @return den Inhalt aller Klassenattribute auf der Konsole ausgeben.
     */
    @Override
    public String toString(){
        return("Name: "+ this.name+
                " IncomingInterest: "+this.incomingInterest+
                " OutgoingInterest: "+ this.outgoingInterest);}

    /**
     *
     * @param other Objekt von Typ PrivateBank
     * @return true falls die beide Objekte gleich sind, sonst false
     */
    @Override
    public boolean equals(Object other) {
        //return(this == other);
        // Identität von this und other prüfen
        if (this == other)
            return true;

        // checking for two condition:
        // 1)prüfen, ob other eine null-Referenz ist.
        // 2)Test auf  Vergleichbarkeit

        if (other == null || this.getClass() != other.getClass())
            return false;

        PrivateBank P = (PrivateBank) other; // type casting object to the

        // checking if the two
        // objects share all the same values
        return this.name.equals(P.name) && this.incomingInterest == P.incomingInterest && this.outgoingInterest == P.outgoingInterest;
    }

    public void createAccount(String account) throws AccountAlreadyExistsException {

        if(!(accountsToTransactions.containsKey(account))){
            List<Transaction> transaction= new ArrayList<Transaction>();
            accountsToTransactions.put(account, transaction);

        }
        else {
            throw new AccountAlreadyExistsException("Account already exists!");
        }
    }
    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException {

        if(accountsToTransactions.containsKey(account)){
            throw new AccountAlreadyExistsException("Account already exists!");
        }
        else {
            accountsToTransactions.put(account, transactions);
        }
    }
    void readAccounts() throws IOException, ClassNotFoundException {
       // this.serialize();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PersistenceSample.json"));
        String data = (String) ois.readObject();
        Gson gson = new Gson();
        Payment payment = gson.fromJson(data, Payment.class);

    }
    public void addTransaction(String account, Transaction transaction) throws AccountDoesNotExistException, TransactionAlreadyExistException{

       if (!accountsToTransactions.containsKey(account)) {
            throw new AccountDoesNotExistException("Account doesn't exist!");
       }
       else if(accountsToTransactions.containsValue(transaction))
       {
           throw new TransactionAlreadyExistException("Transaction already exists!");
       }
       else{
           if(transaction instanceof Payment){
               ((Payment) transaction).setIncomingInterest(this.incomingInterest);
               ((Payment) transaction).setOutgoingInterest(this.outgoingInterest);
           }
           accountsToTransactions.get(account).add(transaction);
        }

    }
    public void removeTransaction(String account, Transaction transaction) throws TransactionDoesNotExistException{
        if(!accountsToTransactions.containsValue(transaction))
        {
            throw new TransactionDoesNotExistException();
        }
        else{
            accountsToTransactions.get(account).remove(transaction);
        }
    }

    public boolean containsTransaction(String account, Transaction transaction){
        if (!accountsToTransactions.containsKey(account)) {
           return false;
        }
        else if(accountsToTransactions.containsValue(transaction))
        {
           return true;
        }
        else{
            return false;
        }

    }

    public double getAccountBalance(String account){

        double betrag = 0.00;
        if(accountsToTransactions.containsKey(account)){
            for(Transaction trans : accountsToTransactions.get(account)){
                betrag += trans.calculate();
            }
        }
        return betrag;
    }


    public List<Transaction> getTransactions(String account){
         List<Transaction> TransactionList = new ArrayList<>(accountsToTransactions.get(account));
         return TransactionList;
     }


    public List<Transaction> getTransactionsSorted(String account, boolean asc){
        List<Transaction> TransactionList = new ArrayList<>(accountsToTransactions.get(account));

        if (asc) {

            TransactionList.sort((Comparator.comparing(Transaction::calculate)));


        } else {

            TransactionList.sort((Comparator.comparing(Transaction::calculate)).reversed());

        }
        return TransactionList;
    }

    public List<Transaction> getTransactionsByType(String account, boolean positive){
        return accountsToTransactions.get(account);
    }


}
