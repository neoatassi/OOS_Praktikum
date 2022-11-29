package bank;

// class declared with default access modifier to grant access to classes inside the same package bank
class Transfer extends Transaction {
    /**
     * Private attributes that are only accessible within the same declared class
     */
    private String sender;
    private String recipient;

    /*  Following methods use default access modifier to grant access to all classes
        inside the same package
     */

    /**
     * Constructor for class Transfer
     * @param date
     * @param amount
     * @param description
     */
    Transfer(String date, double amount, String description){
        super(date, amount, description);
    }

    /**
     * Constructor for class Transfer
     * @param date
     * @param amount
     * @param description
     * @param sender
     * @param recipient
     */
    Transfer(String date, double amount, String description, String sender, String recipient){
        super(date, amount, description);
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * Copy-Constuctor for class Transfer
     * @param transfer
     */
    Transfer(Transfer transfer){
        this(transfer.date, transfer.amount, transfer.description, transfer.sender, transfer.recipient);
    }

    /**
     * sets the sender
     * @param sender
     */
    void setSender(String sender){
        this.sender = sender;
    }

    /**
     * sets the recipient
     * @param recipient
     */
    void setRecipient(String recipient){
        this.recipient = recipient;
    }

    /**
     * gets Recipient
     * @return recipient
     */
    String getRecipient(){
        return this.recipient;
    }

    /**
     * gets sender
     * @return sender
     */
    String getSender(){
        return this.sender;
    }
    /**
     * Calculates value of amount after interests
     * @return post-interest amount.
     */
    @Override
    public double calculate() {
        return this.amount;
    }

    /**
     * Prints out attribute values of object that's invoked upon
     * @return date, amount, description, sender, recipient
     */
    @Override
    public String toString(){
        this.amount = calculate();
        return super.toString() + "Sender: " + this.sender + "\n" + "Recipient: " + this.recipient;
    }

    /**
     * Compares whether two object are the same
     * @param obj
     * @return true if objects are similar, false if not
     */
    @Override
    public boolean equals(Object obj){
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

        Transfer p1 = (Transfer) obj; // type casting object to the
        // intended class type

        // checking if the two
        // objects share all the same values
        return super.equals(p1) &&
                this.sender.equals(p1) &&
                this.recipient.equals(p1);
    }

    /*
    public void printObject(){
        System.out.println(this);
        System.out.println("Date: " + this.date);
        System.out.println("Amount: " + this.amount);
        System.out.println("Description: " + this.description);
        System.out.println("Incoming interest: " + this.sender);
        System.out.println("Outgoing interest: " + this.recipient);
    }

     */
}


/*
    void setDate(int day, int month, int year){
        this.date = day + "." + month + "." + year;
    }

     */