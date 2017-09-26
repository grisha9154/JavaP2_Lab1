import javax.jms.*;

/**
 * Created by Grish on 26.09.2017.
 */
public class DirectMessageReceiver implements MessageListener {
   ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
   JMSConsumer consumer;

   DirectMessageReceiver(){
       try(JMSContext context = factory.createContext("admin","admin")){

       }
   }

    @Override
    public void onMessage(Message message) {

    }
}
