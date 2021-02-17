package com.kliubun;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisConsumer {

    private final int numberOfMessages;

    private final StatefulRedisConnection<String, String> redisConnection;

    public RedisConsumer(
          StatefulRedisConnection<String, String> redisConnection,
          int numberOfMessages
    ) {
        this.redisConnection = redisConnection;
        this.numberOfMessages = numberOfMessages;
    }

    public void readFromQueue() {
        final long start = System.currentTimeMillis();
        final RedisCommands<String, String> redisCommand = redisConnection.sync();
        redisCommand.setTimeout(Duration.of(60, ChronoUnit.SECONDS));
        int i = numberOfMessages;
        while (i > 0) {
            redisCommand.lpop("test_queue");
            // uncomment for reliable queue
            // redisCommand.rpoplpush("test_queue", "working_queue");
            // redisCommand.lpop("working_queue");
            i--;
        }
        final long end = System.currentTimeMillis();
        final double totalTime = (end - start) / 1000.0;
        System.out.println("Total time: " + totalTime + " seconds");
        System.out.println("Throughput: " + numberOfMessages/totalTime + " messages/sec");
        System.out.println("Throughput in bytes: " + (100*numberOfMessages)/totalTime/1024 + " KB/sec");
    }

}
