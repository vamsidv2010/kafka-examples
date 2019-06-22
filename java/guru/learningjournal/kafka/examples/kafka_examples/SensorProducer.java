package guru.learningjournal.kafka.examples.kafka_examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Properties;
public class SensorProducer {

	public static void main(String[] args){
	
	String topicName = "sensordata"; 		
   
	Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092,localhost:9093");
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("partitioner.class", "guru.learningjournal.kafka.examples.kafka_examples.SensorPartitioner");
    props.put("speed.sensor.name", "TSS");
    
	Producer<String, String> producer = new KafkaProducer<String, String>(props);
	
	for( int i =0; i < 10 ; i++){
		producer.send(new ProducerRecord<String, String>(topicName,"SSP"+i,"500"+i));
			}
	for( int i =0; i < 10 ; i++){
		producer.send(new ProducerRecord<String, String>(topicName,"TSS","500TSS"+i));
			}
	producer.close();
	}	
		}