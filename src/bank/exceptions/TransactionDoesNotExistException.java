package bank.exceptions;

public class TransactionDoesNotExistException extends Exception{
    /**
     * using constructor to create an exception object
     * @param Ausgabe
     */
    public TransactionDoesNotExistException(){
        super("Transaction existiert nicht! ");
    }
    public TransactionDoesNotExistException(String Ausgabe){
        super(Ausgabe);
    }
}
