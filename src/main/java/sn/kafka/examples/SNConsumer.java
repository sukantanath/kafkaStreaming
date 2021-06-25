package sn.kafka.examples;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Properties;

public class SNConsumer {
    public static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        Properties consumerProps = new Properties();

        consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG,AppConfigs.consumerApplicationID);
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        consumerProps.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"300000")
    }
}
