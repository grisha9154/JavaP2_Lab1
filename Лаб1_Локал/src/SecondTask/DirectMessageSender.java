package SecondTask;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

/**
 * Created by Grish on 26.09.2017.
 */
public class DirectMessageSender {
    public static void main(String[] args){
        ConnectionFactory factory;

        factory = new ConnectionFactory();

        try {
            JMSContext context = factory.createContext("admin", "admin",JMSContext.CLIENT_ACKNOWLEDGE);
            try {
                factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
                Destination cardsTopic = context.createTopic("BankCardTopic");

                Destination cardsQ = context.createQueue("BankCardQ");

                JMSProducer producer = context.createProducer().setPriority(2);
                JMSProducer producer2 = context.createProducer().setPriority(1);
                TextMessage message = context.createTextMessage();

                for (int i=0;i<5;i++){

                    message.setText("Message "+ i);

                    producer.send(cardsTopic,message);
                }


                message.setText("Message producer");
                message.setStringProperty("symbol","BSTU");
                producer.send(cardsQ,message);
                message.setText("Message producer 2");
                producer2.send(cardsQ,message);

                System.out.println("Placed an information about card transaction to Bank");
            } finally {
                context.close();
            }
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
