package sn.kafka.examples;

class AppConfigs {
    final static String applicationID = "StorageDemo";
    final static String consumerApplicationID = "SN_Consumer";
    final static String bootstrapServers = "localhost:9092,localhost:9093";
    final static String topicName = "invoice";
    final static String topicName1 = "hello-producer-1";
    final static String topicName2 = "hello-producer-2";
    //final static int numEvents = 500000;
    final static int numEvents = 2;
    final static String applicationID_MT = "Multi-Threaded-Producer";
    final static String topicName_MT = "nse-eod-topic";
    final static String kafkaConfigFileLocation = "kafka.properties";
    final static String[] eventFiles = {"data/NSE05NOV2018BHAV.csv","data/NSE06NOV2018BHAV.csv"};
    final static String transaction_id = "Hello-Producer-Transaction";
    public final static String groupID = "PosValidatorGroup";
    public final static String[] sourceTopicNames = {"pos"};
    public final static String validTopicName = "valid-pos";
    public final static String invalidTopicName = "invalid-pos";
}
