package com.kliubun;

import com.dinstone.beanstalkc.BeanstalkClient;
import com.dinstone.beanstalkc.BeanstalkClientFactory;
import com.dinstone.beanstalkc.Configuration;
import com.dinstone.beanstalkc.JobConsumer;
import com.dinstone.beanstalkc.JobProducer;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class Main {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        final RedisPublisher redisPublisher = new RedisPublisher(connection, 10_000);
        redisPublisher.generateLoad();

        final RedisConsumer redisConsumer = new RedisConsumer(connection, 10_000);
        redisConsumer.readFromQueue();

        final RedisPublisherConsumerLatency redisPublisherConsumerLatency = new RedisPublisherConsumerLatency(connection, 10_000);
//        redisPublisherConsumerLatency.measureLatency();

        connection.close();

        Configuration config = new Configuration();
        config.setServiceHost("127.0.0.1");
        config.setServicePort(11300);
        config.setConnectTimeout(2000);
        config.setReadTimeout(3000);
        BeanstalkClientFactory factory = new BeanstalkClientFactory(config);
        JobProducer producer = factory.createJobProducer("test_jobs3");
        JobConsumer consumer = factory.createJobConsumer("test_jobs3");
        final BeanstalkClient beanstalkClient = factory.createBeanstalkClient();
//        final BeanstalkdPublisherConsumerLatency beanstalkdPublisherConsumerLatency = new BeanstalkdPublisherConsumerLatency(producer, consumer, 10_000);
//        beanstalkdPublisherConsumerLatency.measureLatency();

        final BeanstalkdPublisher beanstalkdPublisher = new BeanstalkdPublisher(producer, 10_000);
//        beanstalkdPublisher.generateLoad();
        producer.close();
        final BeanstalkdConsumer beanstalkdConsumer = new BeanstalkdConsumer(consumer, 10_000);
//        beanstalkdConsumer.readFromQueue();
        consumer.close();
    }
}
