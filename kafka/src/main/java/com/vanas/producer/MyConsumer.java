package com.vanas.producer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;


import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @author Vanas
 * @create 2020-05-08 2:18 下午
 */
public class MyConsumer {
    public static void main(String[] args) {
//        1。new
        Properties properties = new Properties();

        properties.setProperty("key.deserializer", " org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("bootstrap.servers", "hadoop130:9092");
        properties.setProperty("group.id", "Idea01");
        properties.setProperty("auto.offset.reset", "earliest");

//        关闭自动提交
//        properties.setProperty("enable.auto.commit", "false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

//        consumer订阅的话题
        consumer.subscribe(Collections.singleton("first"));

        Duration duration = Duration.ofMillis(500);

//        while (true) {
        ConsumerRecords<String, String> poll = consumer.poll(duration);
        for (ConsumerRecord<String, String> record : poll) {
            System.out.println(record);

        }
        //手动提交
        consumer.commitSync();

//        异步提交
        consumer.commitAsync(new OffsetCommitCallback() {
            @Override
            public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                offsets.forEach(
                        (t, o) -> {
                            System.out.println("fenqu" + t + "\noffset:" + o);
                        }

                );
            }
        });
//        }

        consumer.close();

    }
}
