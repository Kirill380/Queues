package com.kliubun;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisPublisher {

    private final int numberOfMessages;

    private final StatefulRedisConnection<String, String> redisConnection;

    public RedisPublisher(
          StatefulRedisConnection<String, String> redisConnection,
          int numberOfMessages
    ) {
        this.redisConnection = redisConnection;
        this.numberOfMessages = numberOfMessages;
    }

    public void generateLoad() {
        final long start = System.currentTimeMillis();
        final RedisCommands<String, String> redisCommand = redisConnection.sync();
        redisCommand.setTimeout(Duration.of(60, ChronoUnit.SECONDS));
        for (int i = 0; i < numberOfMessages; i++) {
            redisCommand.lpush("test_queue",
                  "{ \"content\" : \"test_message_test_message\", \"title\" : \"some_title_title_title_title_title_title_12\" }");
        }
        final long end = System.currentTimeMillis();
        final double totalTime = (end - start) / 1000.0;
        System.out.println("Total time: " + totalTime + " seconds");
        System.out.println("Throughput: " + numberOfMessages/totalTime + " messages/sec");
        System.out.println("Throughput in bytes: " + (100*numberOfMessages)/totalTime/1024 + " KB/sec");
    }
}
