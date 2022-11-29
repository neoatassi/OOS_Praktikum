package bank.exceptions;

public class AccountAlreadyExistsException extends Exception {
    /**
     * using constructor to create an exception object
     * @param Ausgabe
     */
    public AccountAlreadyExistsException(String Ausgabe){
        super(Ausgabe);
    }
}
