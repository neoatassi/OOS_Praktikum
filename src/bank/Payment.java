package bank;

// class declared with default access modifier to grant access to classes inside the same package bank
class Payment extends Transaction {
    /**
     * Private attributes that are only accessible within the same declared class
      */
    private double incomingInterest;
    private double outgoingInterest;

    /*  Following methods use default access modifier to grant access to all classes
        inside the same package
     */

    /**
     * Constructor for class Payment
     * @param date
     * @param amount
     * @param description
     */
    Payment(String date, double amount, String description){
        super(date, amount, description);
    }

    /**
     * Constructor for class Payment
     * @param date
     * @param amount
     * @param description
     * @param incomingInterest
     * @param outgoingInterest
     */
    Payment(String date, double amount, String description, double incomingInterest, double outgoingInterest){
        super(date, amount, description);
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;
    }

    /**
     * Copy-Constructor for class Payment
     * @param payment
     */
    Payment(Payment payment){
        this(payment.date, payment.amount, payment.description, payment.incomingInterest, payment.outgoingInterest);
    }

    /**
     * incomingInterest setter
     * @param incomingInterest
     */
    void setIncomingInterest(double incomingInterest){
        this.incomingInterest = incomingInterest;
    }

    /**
     * outgoingInterest setter
     * @param outgoingInterest
     */
    void setOutgoingInterest(double outgoingInterest){
        this.outgoingInterest = outgoingInterest;
    }

    /**
     * incomingInterest getter
     * @return value of incomingInterest
     */
    double getIncomingInterest(){
        return this.incomingInterest;
    }

    /**
     * outgoingInterest getter
     * @return value of outgoingInterest
     */
    double getOutgoingInterest(){
        return this.outgoingInterest;
    }

    /**
     * Calculates value of amount after interests
     * @return
     */
    @Override
    public double calculate() {
        if(this.amount > 0){
            return this.amount - (this.amount * this.incomingInterest);
        } else if(this.amount < 0){
            return this.amount + (this.amount * this.outgoingInterest);
        } else {
            return 0;
        }
    }

    /**
     * Prints out attributes of the object invoked upon
     * @return date, amount, description, incoming interest, outgoing interest.
     */
    @Override
    public String toString(){
        return super.toString() + "Incoming interest: " + this.incomingInterest + "\n" + "Outgoing interest: " + this.outgoingInterest;
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

        Payment p1 = (Payment) obj; // type casting object to the
        // intended class type

        // checking if the two
        // objects share all the same values
        return super.equals(p1) &&
                this.incomingInterest == p1.incomingInterest &&
                this.outgoingInterest == p1.outgoingInterest;
    }

}



/*
    void setDate(int day, int month, int year){
        this.date = day + "." + month + "." + year;
    }
     */