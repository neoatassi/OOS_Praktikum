package bank.exceptions;

public class TransactionAlreadyExistException extends Exception{
    /**
     * using constructor to create an exception object
     * @param Ausgabe
     */
    public TransactionAlreadyExistException(String Ausgabe){
        super(Ausgabe);
    }
}
