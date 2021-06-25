package sn.kafka.examples;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class SNProducer {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Creating Producer...");
        Properties prop = new Properties();
        //Set property for producer object
        prop.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.applicationID);
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,AppConfigs.transaction_id);
        //create producer
        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(prop);
        producer.initTransactions();

        // start sending message
        logger.info("Sending First Transactions...");
        producer.beginTransaction();
        try {
            for (int i = 0; i < AppConfigs.numEvents; i++) {
                producer.send(new ProducerRecord<>(AppConfigs.topicName1, i, "Sample message-T1 " + i));
                producer.send(new ProducerRecord<>(AppConfigs.topicName2, i, "Sample message-T1 " + i));
            }
            logger.info("Commiting First Transactions...");
            producer.commitTransaction();
        } catch (Exception e){
            logger.info("Exception in  First Transactions...");
            producer.abortTransaction();
            producer.close();
            throw new RuntimeException(e);
        }

        logger.info("Sending Second Transactions...");
        producer.beginTransaction();
        try {
            for (int i = 0; i < AppConfigs.numEvents; i++) {
                producer.send(new ProducerRecord<>(AppConfigs.topicName1, i, "Sample message-T2 " + i));
                producer.send(new ProducerRecord<>(AppConfigs.topicName2, i, "Sample message-T2 " + i));
            }
            logger.info("Commiting Second Transactions...");
            producer.abortTransaction();
        } catch (Exception e){
            logger.info("Exception in Second Transactions...");
            producer.abortTransaction();
            producer.close();
            throw new RuntimeException(e);
        }
        // close producer
        logger.info("All messages sent..");
        producer.close();

    }
}
