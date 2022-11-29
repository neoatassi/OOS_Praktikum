package bank;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomSerializer implements JsonSerializer<Transaction> {

    @Override
    public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj=new JsonObject();
        JsonObject atr=new JsonObject();

        //obj.add("INSTANCE",atr);

        obj.addProperty("CLASSNAME",transaction.getClass().getSimpleName());
        if(transaction instanceof Payment){
            atr.addProperty("incomingInterest", ((Payment) transaction).getIncomingInterest());
            atr.addProperty("outgoingInterest", ((Payment) transaction).getOutgoingInterest());
        }
        else if(transaction instanceof IncomingTransfer){
            atr.addProperty("sender", ((IncomingTransfer) transaction).getSender());
            atr.addProperty("recipient", ((IncomingTransfer) transaction).getRecipient());
        }
        else if(transaction instanceof OutgoingTransfer){
            atr.addProperty("sender", ((OutgoingTransfer) transaction).getSender());
            atr.addProperty("recipient", ((OutgoingTransfer) transaction).getRecipient());
        }
        atr.addProperty("date", transaction.getDate());
        atr.addProperty("amount", transaction.getAmount());
        atr.addProperty("description", transaction.getDescription());

        obj.add("INSTANCE",atr);
        System.out.println("This is the Custom Serializer");

        return obj;
    }

}
