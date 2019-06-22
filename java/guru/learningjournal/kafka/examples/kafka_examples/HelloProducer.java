package guru.learningjournal.kafka.examples.kafka_examples;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HelloProducer {

	 private static final Logger logger = LogManager.getLogger(HelloProducer.class);
	 
	 public static void main(String[] args) throws InterruptedException, ExecutionException {
		 String topicName;
		 int numEvents;
		 		 
		 topicName = args[0];
         numEvents = Integer.valueOf(args[1]);
		 
		 Properties props = new Properties(); 
         props.put(ProducerConfig.CLIENT_ID_CONFIG, "HelloProducer"); 
         props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); 
         props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName()); 
         props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		 		 
         KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(props); 
         
         
         for( int i =0 ; i < numEvents ;i++){
        	 
        	 Future<RecordMetadata> metadata = producer.send(new ProducerRecord<Integer, String>(topicName,i,"Simple message  -" + i),new MyProducerCallBack());
             System.out.println("Asychronous call completed" );
                          
         }
         
     }
}

class MyProducerCallBack implements  Callback{

	public void onCompletion(RecordMetadata recordMetadata, Exception e ) {
		// TODO Auto-generated method stub
		if ( e != null){System.out.println("Asynchronous failed with execption");}
		else {System.out.println("Asynchronous sucessfull with out execption");}
	}
	
			
}
