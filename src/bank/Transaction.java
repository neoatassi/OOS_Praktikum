package bank;

import java.io.Serializable;

abstract class Transaction implements CalculateBill, Serializable {
    /**
     * Protected attributes accessible only by inherited classes.
     */
    protected String date;
    protected double amount;
    protected String description;

    /**
     * Constructor for class Transaction.
     * @param date
     * @param amount
     * @param description
     */
    Transaction(String date, double amount, String description){
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    /**
     * date Setter
     * @param date
     */
    void setDate(String date){
        this.date = date;
    }

    /**
     * amount setter
     * @param amount
     */
    void setAmount(double amount){
        this.amount = amount;
    }

    /**
     * description setter
     * @param description
     */
    void setDescription(String description){
        this.description = description;
    }

    /**
     * date getter
     * @return date
     */
    String getDate(){
        return this.date;
    }

    /**
     * amount getter
     * @return amount
     */
    double getAmount(){
        return this.amount;
    }

    /**
     * description getter
     * @return description
     */
    String getDescription(){
        return this.description;
    }


    /**
     * toString method that prints out attributes of the object invoked upon
     * @return date, description, amount
     */
    @Override
    public String toString(){
        return "Date: "+ this.date + "\n" + "Description: " + this.description + "\n" + "Amount: "+ this.calculate() + "\n";
    }

    /**
     * equals() method compares whether two objects are the same
     * @param obj
     * @return true if objects are similar, false if not
     */
    @Override public boolean equals(Object obj)
    {

        // checking if the two objects
        // pointing to same object
        if (this == obj)
            return true;

        // checking for two condition:
        // 1) object is pointing to null
        // 2) if the objects belong to
        // same class or not
        if (obj == null
                || this.getClass() != obj.getClass())
            return false;

        Transaction p1 = (Transaction) obj; // type casting object to the
        // intended class type

        // checking if the two
        // objects share all the same values
        return this.date == p1.date
                && this.amount == p1.amount
                && this.description.equals(p1.description);
    }


}
