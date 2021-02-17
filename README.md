# Beanstalkd 

## Publish 100
Message size: 100 bytes <br/>
Number of messages: 10 000

| Metrics/Iteration            | 1      | 2      | 3      | 4      | 5      |
|------------------------------|--------|--------|--------|--------|--------|
| Total time (sec)             | 11.288 | 10.890 | 10.872 | 10.966 | 10.541 |
| Throughput (m/sec)           | 886    | 918    | 920    | 911    | 949    |
| Throughput in bytes (KB/sec) | 90     | 93.262 | 93.416 | 92.615 | 96.349 |

Avg throughput 917 messages per second (+0)

## Consume 100
Message size: 100 bytes <br/>
Number of messages: 10 000

| Metrics/Iteration            | 1      | 2      | 3      | 4      | 5      |
|------------------------------|--------|--------|--------|--------|--------|
| Total time (sec)             | 26.269 | 20.601 | 21.360 | 22.041 | 24.850 |
| Throughput (m/sec)           | 381    | 485    | 468    | 454    | 402    |
| Throughput in bytes (KB/sec) | 38.662 | 49.300 | 47.548 | 46.080 | 40.870 |

Avg throughput 438 messages per second


## Publish-Consume latency 100
Message size: 100 bytes <br/>
Number of messages: 10 000

* Iteration 1: 3.1878 ms
* Iteration 2: 3.4046 ms
* Iteration 3: 3.3900 ms
* Iteration 4: 3.1548 ms
* Iteration 5: 3.3571 ms

Avg latency 3 ms


# Redis RDB

## Publish 100
Message size: 100 bytes <br/>
Number of messages: 10 000

| Metrics/Iteration            | 1      | 2      | 3      | 4      | 5      |
|------------------------------|--------|--------|--------|--------|--------|
| Total time (sec)             | 11.670 | 11.645 | 11.586 | 11.361 | 11.434 |
| Throughput (m/sec)           | 857    | 859    | 863    | 880    | 874    |
| Throughput in bytes (KB/sec) | 83.681 | 83.861 | 84.288 | 85.957 | 85.408 |

Avg throughput 867 messages per second (-50)

## Consume 100
Message size: 100 bytes <br/>
Number of messages: 10 000

| Metrics/Iteration            | 1      | 2      | 3      | 4      | 5      |
|------------------------------|--------|--------|--------|--------|--------|
| Total time (sec)             | 11.021 | 10.497 | 11.844 | 11.176 | 11.619 |
| Throughput (m/sec)           | 907    | 953    | 844    | 894    | 860    |
| Throughput in bytes (KB/sec) | 88.609 | 93.033 | 82.452 | 87.380 | 84.049 |

Avg throughput 892 messages per second

## Consume 100 (reliable queue)
Message size: 100 bytes <br/>
Number of messages: 10 000

| Metrics/Iteration            | 1      | 2      | 3      | 4      | 5      |
|------------------------------|--------|--------|--------|--------|--------|
| Total time (sec)             | 27.904 | 23.858 | 20.580 | 21.433 | 21.373 |
| Throughput (m/sec)           | 358    | 419    | 489    | 467    | 468    |
| Throughput in bytes (KB/sec) | 35.000 | 40.932 | 47.452 | 45.563 | 45.691 |

Avg throughput 440 messages per second


## Publish-Consume latency 100
Message size: 100 bytes <br/>
Number of messages: 10 000

* Iteration 1: 2.2702 ms
* Iteration 2: 2.4228 ms
* Iteration 3: 2.2693 ms
* Iteration 4: 2.3465 ms
* Iteration 5: 2.3734 ms

Avg latency 2 ms

# Redis AOF (fsync 1 sec)

## Publish 100
Message size: 100 bytes <br/>
Number of messages: 10 000

| Metrics/Iteration            | 1      | 2      | 3      | 4      | 5      |
|------------------------------|--------|--------|--------|--------|--------|
| Total time (sec)             | 14.409 | 14.450 | 15.712 | 14.449 | 14.285 |
| Throughput (m/sec)           | 694    | 692    | 636    | 692    | 700    |
| Throughput in bytes (KB/sec) | 67.774 | 67.582 | 62.154 | 67.586 | 68.362 |

Avg throughput 683 messages per second (-234) (-184)


## Consume 100
Message size: 100 bytes <br/>
Number of messages: 10 000

| Metrics/Iteration            | 1      | 2      | 3      | 4      | 5      |
|------------------------------|--------|--------|--------|--------|--------|
| Total time (sec)             | 14.054 | 13.385 | 14.633 | 16.653 | 16.695 |
| Throughput (m/sec)           | 712    | 747    | 683    | 601    | 599    |
| Throughput in bytes (KB/sec) | 69.486 | 73.000 | 66.737 | 58.642 | 58.494 |

Avg throughput 668 messages per second

