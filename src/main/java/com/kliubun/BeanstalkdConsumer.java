package com.kliubun;

import com.dinstone.beanstalkc.Job;
import com.dinstone.beanstalkc.JobConsumer;

public class BeanstalkdConsumer {

    private final int numberOfMessages;

    private final JobConsumer jobConsumer;

    public BeanstalkdConsumer(
          JobConsumer jobConsumer,
          int numberOfMessages
    ) {
        this.jobConsumer = jobConsumer;
        this.numberOfMessages = numberOfMessages;
    }

    public void readFromQueue() {
        final long start = System.currentTimeMillis();

        for (int i = 0; i < numberOfMessages; i++) {
            final Job job = jobConsumer.reserveJob(100);
            jobConsumer.deleteJob(job.getId());
        }
        final long end = System.currentTimeMillis();
        final double totalTime = (end - start) / 1000.0;
        System.out.println("Total time: " + totalTime + " seconds");
        System.out.println("Throughput: " + numberOfMessages/totalTime + " messages/sec");
        System.out.println("Throughput in bytes: " + (104*numberOfMessages)/totalTime/1024 + " KB/sec");
    }

}
