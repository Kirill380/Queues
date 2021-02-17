package com.kliubun;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisPublisherConsumerLatency {

    private final int numberOfMessages;

    private final StatefulRedisConnection<String, String> redisConnection;

    public RedisPublisherConsumerLatency(
          StatefulRedisConnection<String, String> redisConnection,
          int numberOfMessages
    ) {
        this.redisConnection = redisConnection;
        this.numberOfMessages = numberOfMessages;
    }

    public void measureLatency() {
        final RedisCommands<String, String> redisCommand = redisConnection.sync();
        redisCommand.setTimeout(Duration.of(60, ChronoUnit.SECONDS));
        double totalTime = 0.0;
        for (int i = 0; i < numberOfMessages; i++) {
            final long start = System.currentTimeMillis();
            redisCommand.lpush("test_queue",
                  "{ \"content\" : \"test_message_test_message\", \"title\" : \"some_title_title_title_title_title_title_12\" }");
            redisCommand.lpop("test_queue");
            final long end = System.currentTimeMillis();
            totalTime = totalTime + (end - start);
        }

        System.out.println("Average latency: " + totalTime/numberOfMessages);
    }
}
