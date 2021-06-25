package sn.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Disptacher implements Runnable{
    private static final Logger logger = LogManager.getLogger();
    private String fileLocation;
    private String topicName;
    private KafkaProducer<Integer, String> producer;

    Disptacher(KafkaProducer<Integer, String> producer, String fileLocation, String topicName){
        this.producer = producer;
        this.fileLocation = fileLocation;
        this.topicName = topicName;
    }


    @Override
    public void run(){
        logger.info("Starting processing from "+ fileLocation);
        int cntr = 0;
        File file = new File(fileLocation);
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                producer.send(new ProducerRecord<>(topicName,null,line));
                cntr++;
            }
            logger.info("Finished sedning "+ cntr + " messages from "+ fileLocation);
        } catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

    }
}
