package SecondTask;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

/**
 * Created by Grish on 27.09.2017.
 */
public class firstMessageReceiver implements MessageListener {
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;
    JMSConsumer q;

    firstMessageReceiver(){
        try(JMSContext context = factory.createContext("admin","admin")){
            factory.setProperty(ConnectionConfiguration.imqAddressList,"mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination cardsQueue = context.createTopic("BankCardTopic");
            Destination cardsQ = context.createTopic("BankCardQ");
            String selector = "symbol<>BSTU";
            consumer = context.createConsumer(cardsQueue,selector);
            consumer.setMessageListener(this);
            System.out.println("Listening to theBankCardTopic...");

            context.acknowledge();

            q = context.createConsumer(cardsQ);
            q.setMessageListener(this);
            Thread.sleep(100000);

        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void onMessage(Message message) {
        try{
            System.out.println("FirstListener: "+ message.getBody(String.class));

        } catch (JMSException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        new firstMessageReceiver();
    }
}