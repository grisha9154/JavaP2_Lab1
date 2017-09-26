import javax.jms.*;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
/**
 * Created by Grish on 26.09.2017.
 */
public class DirectMessageSender {
    public static void main(String[] args){
        ConnectionFactory factory;

        factory = new ConnectionFactory();

        try(JMSContext context = factory.createContext("admin","admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,"mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination cardsQueue = context.createQueue("BankCardQueue");
            JMSProducer producer = context.createProducer();

            producer.send(cardsQueue,"PNV 100 5634234");

            System.out.println("Placed an information about card transaction to Bank");
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
