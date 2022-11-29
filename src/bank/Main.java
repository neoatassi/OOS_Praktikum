package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;

/**
 *  @author Atassi.
 *
 */

public class Main {
    /**
     *
     * @param args
     */
    public static void main (String[] args){

        // Declaration of a new Payment objects and initialization through "all attributes" constructor
        Payment complete_deposit = new Payment("11.05.2021", 1000, "deposit", 0.05, 0.1);
        Payment complete_withdrawal = new Payment("23.06.2021", -1000, "withdrawal", 0.05, 0.1);

        // Declaration of new Payment objects and initialization through "three attributes" constructor
        Payment partial_deposit = new Payment("03.01.1998", 3000, "partial deposit");
        Payment partial_withdrawal = new Payment("10.03.2008", -3000, "partial withdrawal");

        // Declaration of new Payment objects and initialization through copy constructor
        Payment complete_deposit_copy = new Payment(complete_deposit);
        Payment partial_withdrawal_copy = new Payment(partial_withdrawal);

        // Declaration of a new Transfer objects and initialization through "all attributes" constructor
        Transfer transfer1 = new Transfer("15.05.2001", 140, "First transfer", "Max", "Sam");
        Transfer transfer2 = new Transfer("31.04.2021", 340, "Second transfer", "Andrew", "Joe");

        // Declaration of new Transfer objects and initialization through "three attributes" constructor
        Transfer transfer3 = new Transfer("21.09.2005", 405, "Third transfer");
        Transfer transfer4 = new Transfer("17.02.1995", 300, "Fourth transfer");

        // Declaration of new Transfer objects and initialization through copy constructor
        Transfer transfer1_copy = new Transfer(transfer1);
        Transfer transfer3_copy = new Transfer(transfer3);

        /*System.out.println("Calculated amounts: ");

        System.out.println(complete_deposit.calculate());
        System.out.println(complete_withdrawal.calculate());
        System.out.println(transfer1.calculate());
        System.out.println(transfer4.calculate());

        System.out.println("Testing toString() ");

        System.out.println(partial_withdrawal.toString());
        System.out.println(partial_withdrawal_copy.toString());
        System.out.println(transfer1.toString());
        System.out.println(transfer1_copy.toString());


         */

        PrivateBank musterbank = new PrivateBank("MusterBank", 0.3, 0.1);
        PrivateBankAlt altmusterbank = new PrivateBankAlt("AltMusterBank", 0.3, 0.1);


        try {
            musterbank.createAccount("Atassi");
            musterbank.addTransaction("Atassi", complete_deposit);
            musterbank.addTransaction("Atassi", partial_deposit);
            musterbank.addTransaction("Atassi", transfer2);
           // System.out.println("Account balance of Atassi of Musterbank is " + musterbank.getAccountBalance("Atassi"));

            musterbank.getTransactions("Atassi");


            altmusterbank.createAccount("Alternative");
            altmusterbank.addTransaction("Alternative", complete_deposit);
            altmusterbank.addTransaction("Alternative", partial_deposit);
            altmusterbank.addTransaction("Alternative", transfer2);
            //System.out.println("Account balance of Alternative of AltMusterbank is " + altmusterbank.getAccountBalance("Alternative"));

            /*

            Gson gson = new Gson();
            String json = gson.toJson(partial_deposit, Payment.class);
            System.out.println("Printing json: " + json);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("trial.txt"));
            oos.writeObject(complete_deposit);
            oos.close();

            Gson gson1 = new Gson();


            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PersistenceSample.json"));
            String data = (String) ois.readObject();
            //Payment streamedPayment = gson1.fromJson(ois, Payment.class);
            ois.close();
            //System.out.println("streamedPayment: " + streamedPayment);

             */

            JsonSerializer<Transaction> serializer = new JsonSerializer<Transaction>() {
                @Override
                public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {
                    System.out.println("This is the CustomSerializer in main");
                    return null;
                }
            };
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Transaction.class, serializer).create();

            Gson customgson = builder.create();


            String customJson = customgson.toJson(complete_withdrawal);
            System.out.println(customJson);





        } catch (TransactionAlreadyExistException e) {
            e.printStackTrace();
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        } catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
        //} catch (FileNotFoundException e){
            //e.printStackTrace();
        //} catch (IOException e){
          //  e.printStackTrace();
       // } catch (ClassNotFoundException e){
          //  e.printStackTrace();
        }


    }
}
