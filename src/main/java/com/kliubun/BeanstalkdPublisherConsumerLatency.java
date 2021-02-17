package com.kliubun;

import com.dinstone.beanstalkc.Job;
import com.dinstone.beanstalkc.JobConsumer;
import com.dinstone.beanstalkc.JobProducer;

public class BeanstalkdPublisherConsumerLatency {

    private final int numberOfMessages;

    private final JobProducer jobProducer;

    private final JobConsumer jobConsumer;

    public BeanstalkdPublisherConsumerLatency(
          JobProducer producer,
          JobConsumer consumer,
          int numberOfMessages
    ) {
        this.jobProducer = producer;
        this.jobConsumer = consumer;
        this.numberOfMessages = numberOfMessages;
    }

    public void measureLatency() {
        double totalTime = 0.0;
        for (int i = 0; i < numberOfMessages; i++) {
            final long start = System.currentTimeMillis();
            jobProducer.putJob(0, 0, 10000, ("{ \"content\" : \"test_message_test_message\", \"title\" : \"some_title_title_title_title_title_title_12\" }").getBytes());
            final Job job = jobConsumer.reserveJob(100);
            jobConsumer.deleteJob(job.getId());
            final long end = System.currentTimeMillis();
            totalTime = totalTime + (end - start);
        }

        System.out.println("Average latency: " + totalTime/numberOfMessages);
    }
}
