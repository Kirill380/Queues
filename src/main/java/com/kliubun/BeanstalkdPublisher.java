package com.kliubun;

import com.dinstone.beanstalkc.JobProducer;

public class BeanstalkdPublisher {

    private final int numberOfMessages;

    private final JobProducer jobProducer;

    public BeanstalkdPublisher(
          JobProducer jobProducer,
          int numberOfMessages
    ) {
        this.jobProducer = jobProducer;
        this.numberOfMessages = numberOfMessages;
    }

    public void generateLoad() {
        final long start = System.currentTimeMillis();

        for (int i = 0; i < numberOfMessages; i++) {
            jobProducer.putJob(0, 0, 10000, ("{ \"content\" : \"test_message_test_message\", \"title\" : \"some_title_title_title_title_title_title_12\" }").getBytes());
        }
        final long end = System.currentTimeMillis();
        final double totalTime = (end - start) / 1000.0;
        System.out.println("Total time: " + totalTime + " seconds");
        System.out.println("Throughput: " + numberOfMessages/totalTime + " messages/sec");
        System.out.println("Throughput in bytes: " + (104*numberOfMessages)/totalTime/1024 + " KB/sec");
    }

}
