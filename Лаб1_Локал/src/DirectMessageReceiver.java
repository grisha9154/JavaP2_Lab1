import javax.jms.*;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

/**
 * Created by Grish on 26.09.2017.
 */
public class DirectMessageReceiver /*implements MessageListener*/ {
   ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
   JMSConsumer consumer;

   DirectMessageReceiver(){
       try(JMSContext context = factory.createContext("admin","admin")){
        factory.setProperty(ConnectionConfiguration.imqAddressList,"mq://127.0.0.1:7676,mq://127.0.0.1:7676");
        Destination cardsQueue = context.createQueue("BankCardQueue");
        consumer = context.createConsumer(cardsQueue);
      //  consumer.setMessageListener(this);
           System.out.println("Listening to theBankCardQueue...");
           receive();

           Thread.sleep(6000);

       } catch (JMSException e) {
           System.out.println("Error: " + e.getMessage());
       } catch (InterruptedException e) {
           System.out.println("Error: " + e.getMessage());
       }
   }

   public void receive() {
       try {
           Message msg = consumer.receive();
           System.out.println("Sinc method: " + msg.getBody(String.class));
       } catch (JMSException e) {
           System.out.println("Error: "+ e.getMessage());
            }
   }

   /* @Override
    public void onMessage(Message message) {
        try{
            System.out.println("Got the text message from the BankCardQueue: "+ message.getBody(String.class));

            System.out.println("\n =Here's what toString() on the message prints \n" + message);
        } catch (JMSException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
*/
public static void main(String[] args){
      DirectMessageReceiver receiver = new DirectMessageReceiver();
}
}


