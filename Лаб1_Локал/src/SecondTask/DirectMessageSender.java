package SecondTask;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;

/**
 * Created by Grish on 26.09.2017.
 */
public class DirectMessageSender {
    public static void main(String[] args){
        ConnectionFactory factory;

        factory = new ConnectionFactory();

        try {
            JMSContext context = factory.createContext("admin", "admin");
            try {
                factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
                Destination cardsTopic = context.createTopic("BankCardTopic");
                JMSProducer producer = context.createProducer();

                for (int i=0;i<5;i++){
                    producer.send(cardsTopic, "Message "+ i);
                }

                System.out.println("Placed an information about card transaction to Bank");
            } finally {
                context.close();
            }
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
